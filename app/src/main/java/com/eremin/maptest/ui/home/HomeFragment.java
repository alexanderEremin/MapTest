package com.eremin.maptest.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eremin.maptest.R;
import com.eremin.maptest.data.ConstantManager;
import com.eremin.maptest.databinding.FragmentHomeBinding;
import com.eremin.maptest.interfaces.IClickRecycler;
import com.eremin.maptest.pojo.yandexsearch.Feature;
import com.eremin.maptest.ui.adapter.AdapterListSchools;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IClickRecycler {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MapView mapView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView info;
    private MaterialButton getPreferences;
    private MotionLayout motionLayout;
    private MapController mapController;
    private Marker markerUser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = binding.mapView;
        markerUser = new Marker(mapView);
        progressBar = binding.progress;
        info = binding.message;
        motionLayout = binding.motion;
        getPreferences = binding.preferences;
        getPreferences.setOnClickListener(v -> checkPermissions());
        recyclerView = binding.listSchools;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
        checkPermissions();
    }

    /**
     * Инициализация модели происходит только если доступно получение местоположения
     */
    private void initViewModel(){
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.startReseptionLocation();
        homeViewModel.getStatusFirstDownloadLoacation().observe(getViewLifecycleOwner(), data ->{
            if(data)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        });
        homeViewModel.getCoordinates().observe(getViewLifecycleOwner(), data -> {
            addMarkerUser(data.get(ConstantManager.LAST_LATTITUDE), data.get(ConstantManager.LAST_LONGITUDE));
        });
        homeViewModel.getInfo().observe(getViewLifecycleOwner(), data -> {
            info.setText(data);
        });
        homeViewModel.getListSchools().observe(getViewLifecycleOwner(), data -> {
            recyclerView.setAdapter(new AdapterListSchools(data, this));
            for (Feature res : data){
                addMarkerSchool(res.getProperties().getName(),
                                res.getGeometry().getCoordinates().get(1),
                                res.getGeometry().getCoordinates().get(0));
            }
        });
    }

    /**
     * Первоначальная настройка карты
     * центром выставлена Москва
     */
    private void initMap(){
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);
        mapController = (MapController) mapView.getController();
        GeoPoint point = new GeoPoint(55.7522,37.6156);
        mapController.setCenter(point);
        mapController.setZoom(14);
    }
    /**
     * Добавить маркер пользователя на карту
     * @param lattitude латтитуда
     * @param longitude лонгитуда
     */
    private void addMarkerUser(Double lattitude, Double longitude){
        markerUser.remove(mapView); // Удаление прошлого маркера

        markerUser = new Marker(mapView); // Создание нового маркера

        GeoPoint point = new GeoPoint(lattitude, longitude);
        mapController.animateTo(point);
        mapController.setZoom(16);

        markerUser.setPosition(point);

        markerUser.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_checked_24, null));

        mapView.getOverlays().add(markerUser); // рисуем маркер
    }
    /**
     * Добавить маркер пользователя на карту
     * @param lattitude латтитуда
     * @param longitude лонгитуда
     */
    private void addMarkerSchool(String name, Double lattitude, Double longitude){
        Marker mMarker = new Marker(mapView);

        GeoPoint point = new GeoPoint(lattitude, longitude);
        mMarker.setPosition(point);

        mMarker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_school_24, null));
        mMarker.setTitle(name);
        mapView.getOverlays().add(mMarker); // рисуем маркер
    }
    /**
     * Перемещение к определенному маркеру
     * @param lattitude латтитуда
     * @param longitude лонгитуда
     */
    private void animateMovingToMarker(Double lattitude, Double longitude){
        GeoPoint point = new GeoPoint(lattitude, longitude);
        mapController.animateTo(point);
    }

    /**
     * Проверка на наличие разрешений для получения координат
     * Если разрешений нет - запрашиваем
     * Если есть - запрашиваем координаты
     */
    private void checkPermissions(){
        System.out.println("-----------------checkPermissions");
        if (
                ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            List<String> permissions = new ArrayList<>();
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            String[] permissionArray = new String[permissions.size()];
            permissions.toArray(permissionArray);
            requestPermissions(permissionArray, ConstantManager.REQUEST_CODE_PERMISSIONS_LOCATION);
        }else {
            initViewModel();
        }
    }
    /**
     * Обработка разрешений на локацию
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == ConstantManager.REQUEST_CODE_PERMISSIONS_LOCATION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                getPreferences.setVisibility(View.GONE);
                initViewModel();
            }else {
                getPreferences.setVisibility(View.VISIBLE);
                info.setText("Для работы приложения требуются разрешения на геолокацию");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Обработка клика по школе из списка
     * @param lattitude
     * @param longitude
     */
    @Override
    public void clickCard(double lattitude, double longitude) {
        animateMovingToMarker(lattitude, longitude);
    }
}