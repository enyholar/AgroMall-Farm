package com.behruz.agromall_farms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.databinding.ItemFarmBinding;
import com.behruz.agromall_farms.model.FarmerFarm;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gideon on 27/08/19.
 */

public class FarmerFarmAdapter extends RecyclerView.Adapter<FarmerFarmAdapter.FarmerFarmViewHolder> {

    private List<FarmerFarm> farmerFarmList;
    private Context context;
    int selected_position = -1;
    private ClickListner clickListner;

    public FarmerFarmAdapter(Context context, ClickListner listner) {
        this.context = context;
        farmerFarmList = new ArrayList<>();
        this.clickListner = listner;
    }

    private void add(FarmerFarm item) {
        farmerFarmList.add(item);
        notifyItemInserted(farmerFarmList.size() - 1);
    }

    public void addAll(List<FarmerFarm> movieDatas) {
        for (FarmerFarm movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(FarmerFarm item) {
        int position = farmerFarmList.indexOf(item);
        if (position > -1) {
            farmerFarmList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public FarmerFarm getItem(int position) {
        return farmerFarmList.get(position);
    }

    @Override
    public FarmerFarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFarmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_farm, parent, false);

        final FarmerFarmViewHolder farmFarmerViewHolder = new FarmerFarmViewHolder(binding);
        return farmFarmerViewHolder;
    }

    @Override
    public void onBindViewHolder(FarmerFarmViewHolder holder, final int position) {
        final FarmerFarm model = farmerFarmList.get(position);
        holder.bind(model,position);
    }

    @Override
    public int getItemCount() {
        return farmerFarmList.size();
    }

    public class FarmerFarmViewHolder extends RecyclerView.ViewHolder {
        private ItemFarmBinding binding;
        public FarmerFarmViewHolder(ItemFarmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final FarmerFarm model,int position) {
            binding.name.setText(model.getFarmName());
            binding.address.setText(model.getFarmAddress());
            binding.getRoot().setOnClickListener(view -> clickListner.onItemClick(model,position));
        }


    }

    public interface ClickListner {
        void onItemClick(FarmerFarm model, int position);
    }

}
