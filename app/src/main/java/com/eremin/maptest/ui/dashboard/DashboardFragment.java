package com.eremin.maptest.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eremin.maptest.R;
import com.eremin.maptest.databinding.FragmentDashboardBinding;
import com.google.android.material.button.MaterialButton;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        final TextView textTwo = binding.textTwo;
        dashboardViewModel.getLattitude().observe(getViewLifecycleOwner(), data -> {
            textView.setText(String.valueOf(data));
        });
        dashboardViewModel.getLongitude().observe(getViewLifecycleOwner(), data -> {
            textTwo.setText(String.valueOf(data));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}