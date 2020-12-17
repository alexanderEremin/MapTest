package com.eremin.maptest.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eremin.maptest.R;
import com.eremin.maptest.interfaces.IClickRecycler;
import com.eremin.maptest.pojo.yandexsearch.Feature;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterListSchools extends RecyclerView.Adapter<AdapterListSchools.ListSchoolsHolder> {
    private List<Feature> data;
    private IClickRecycler resultClick;
    public AdapterListSchools(List<Feature> data, IClickRecycler resultClick){
        this.data = data;
        this.resultClick = resultClick;
    }
    @NonNull
    @NotNull
    @Override
    public ListSchoolsHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_school, parent, false);
        return new ListSchoolsHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSchoolsHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            resultClick.clickCard(data.get(position).getGeometry().getCoordinates().get(1),data.get(position).getGeometry().getCoordinates().get(0));
        });
        holder.name.setText(data.get(position).getProperties().getName());
        holder.address.setText(data.get(position).getProperties().getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ListSchoolsHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView address;
        public ListSchoolsHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
        }

    }
}
