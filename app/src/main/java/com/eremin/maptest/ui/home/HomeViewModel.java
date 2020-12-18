package com.eremin.maptest.ui.home;

import android.app.Application;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.ArrayMap;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.eremin.maptest.App;
import com.eremin.maptest.data.ConstantManager;
import com.eremin.maptest.interfaces.IForSputnik;
import com.eremin.maptest.interfaces.IForYandex;
import com.eremin.maptest.pojo.sputnikssearsh.Main;
import com.eremin.maptest.pojo.yandexsearch.Feature;
import com.eremin.maptest.pojo.yandexsearch.MainSearch;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mStatusFirstDownloadLoacation;
    private MutableLiveData<Boolean> mErrorDownloadLoacation;
    private MutableLiveData<String> mInfo;
    private MutableLiveData<List<Feature>> mListSchools;
    private final MutableLiveData<ArrayMap<String, Double>> coordinates;
    private double lattitude, longitude;
    private String address;

    @Inject IForSputnik mLoadFromSputnik;
    @Inject IForYandex mLoadFromYandex;
    @Inject CompositeDisposable mCompositeDisposable;
    @Inject SharedPreferences sharedPreferences;
    @Inject Observable<Location> locationObservable;

    public HomeViewModel(Application application) {
        super(application);
        ((App) application).getAppComponent().injectToViewModel(this);
        mStatusFirstDownloadLoacation = new MutableLiveData<>();
        mErrorDownloadLoacation = new MutableLiveData<>();
        coordinates = new MutableLiveData<>();
        mInfo = new MutableLiveData<>();
        mListSchools = new MutableLiveData<>();
        /**
         * Если есть записи о прошлой локации, то покажем старую точку на карте
         */
        if (sharedPreferences.contains(ConstantManager.LAST_LATTITUDE) && sharedPreferences.contains(ConstantManager.LAST_LONGITUDE)){
            setLattitudeAndLongitude(sharedPreferences.getFloat(ConstantManager.LAST_LATTITUDE, 0),
                                    sharedPreferences.getFloat(ConstantManager.LAST_LONGITUDE, 0));
            jobCoordinates(lattitude, longitude);
        }
    }

    public MutableLiveData<Boolean> getStatusFirstDownloadLoacation() {
        return mStatusFirstDownloadLoacation;
    }
    public MutableLiveData<ArrayMap<String, Double>> getCoordinates() {
        return coordinates;
    }
    public MutableLiveData<String> getInfo() {
        return mInfo;
    }
    public MutableLiveData<List<Feature>> getListSchools() {
        return mListSchools;
    }
    public MutableLiveData<Boolean> getmErrorDownloadLoacation() {
        return mErrorDownloadLoacation;
    }

    /**
     * Установить данные по широте и долготе
     * @param lattitude
     * @param longitude
     */
    private void setLattitudeAndLongitude(double lattitude, double longitude){
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Получение координат
    */
    public void startReseptionLocation() {
        mStatusFirstDownloadLoacation.setValue(true);
        mInfo.setValue("Определяем Ваши координаты");
        mCompositeDisposable.add(locationObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> {
                    setLattitudeAndLongitude(next.getLatitude(), next.getLongitude());
                    jobCoordinates(lattitude, longitude);
                    mErrorDownloadLoacation.setValue(false);
                    mStatusFirstDownloadLoacation.setValue(false);
                    loadAddressFromLocation(next.getLatitude(), next.getLongitude());
                }, error -> {
                    mStatusFirstDownloadLoacation.setValue(false);
                    mErrorDownloadLoacation.setValue(true);
                    mInfo.setValue("Ошибка определения местоположения");
                }));

    }

    /**
     * Обработка полученных координат
     * 1. Записываем в sharedPreferences
     * 2. Изменяем рабочие данные по координатам
     * @param lattitude латитуда
     * @param longitude лонгитуда
     */
    private void jobCoordinates(double lattitude, double longitude){
        sharedPreferences.edit().putFloat(ConstantManager.LAST_LATTITUDE, (float) lattitude).apply();
        sharedPreferences.edit().putFloat(ConstantManager.LAST_LONGITUDE, (float) longitude).apply();
        ArrayMap<String, Double> hashMap = new ArrayMap<>();
        hashMap.put(ConstantManager.LAST_LATTITUDE, lattitude);
        hashMap.put(ConstantManager.LAST_LONGITUDE, longitude);
        coordinates.setValue(hashMap);
    }

    /**
     * Обработка адреса по координатам
     * @param lattitude латитуда
     * @param longitude лонтитуда
     */
    private void loadAddressFromLocation(double lattitude, double longitude){
        mStatusFirstDownloadLoacation.setValue(true);
        mInfo.setValue("Определяем Ваш адрес");
        mCompositeDisposable.add(mLoadFromSputnik.getAddressUser(lattitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::success, this::error));
    }

    /**
     * Данные адреса местонахождения успешно загрущены
     * @param main pojo/sputnik
     */
    private void success(Main main) {
        mStatusFirstDownloadLoacation.setValue(false);
        setAddress(main.getResult().getAddress().get(0).getFeatures().get(0).getProperties().getDisplayName());
        mInfo.setValue(address);
        loadListSchool(address, lattitude, lattitude);
    }

    /**
     * Ошибка загрузки данных
     * @param throwable ошибка загрузки
     */
    private void error(Throwable throwable) {
        throwable.printStackTrace();
        mStatusFirstDownloadLoacation.setValue(false);
        mInfo.setValue("Ошибка определения адреса");

    }

    /**
     * Загрузка списка школ рядом с пользователем
     * @param address адрес, что был определен ранее по координатам
     * @param lattitude лититуда
     * @param longitude лонгитутда
     */
    private void loadListSchool(String address, double lattitude, double longitude){
        mStatusFirstDownloadLoacation.setValue(true);
        mInfo.setValue("Определяем школы рядом с Вами");
        mCompositeDisposable.add(mLoadFromYandex.getListSchools("школы "+address,
                                                                  lattitude+","+longitude,
                                                                "ru_RU",
                                                                     ConstantManager.API_KEY_YANDEX)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::successListSchool, this::errorListSchool));
    }

    /**
     * Загрузка списка школ удалась
     * @param main загруженные данные
     */
    private void successListSchool(MainSearch main) {
        mStatusFirstDownloadLoacation.setValue(false);
        mInfo.setValue(address);
        mListSchools.setValue(main.getFeatures());
    }

    /**
     * Произошла ошибка при загрузке данных по школам
     * @param throwable ошибка при загрузке
     */
    private void errorListSchool(Throwable throwable) {
        throwable.printStackTrace();
        mStatusFirstDownloadLoacation.setValue(false);
        mInfo.setValue("Ошибка загрузки школ что находятся рабом с Вами");
    }
}