package com.behruz.agromall_farms.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.databinding.ItemFarmerBinding;
import com.behruz.agromall_farms.model.Farmer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gideon on 27/08/19.
 */

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmViewHolder> {

    private List<Farmer> farmList;
    private Context context;
    int selected_position = -1;
    private ClickListner clickListner;

    public FarmAdapter(Context context, ClickListner listner) {
        this.context = context;
        farmList = new ArrayList<>();
        this.clickListner = listner;
    }

    private void add(Farmer item) {
        farmList.add(item);
        notifyItemInserted(farmList.size() - 1);
    }

    public void addAll(List<Farmer> movieDatas) {
        for (Farmer movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(Farmer item) {
        int position = farmList.indexOf(item);
        if (position > -1) {
            farmList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Farmer getItem(int position) {
        return farmList.get(position);
    }

    @Override
    public FarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFarmerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_farmer, parent, false);

        final FarmViewHolder movieViewHolder = new FarmViewHolder(binding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(FarmViewHolder holder, final int position) {
        final Farmer model = farmList.get(position);
        holder.bind(model,position);
    }

    @Override
    public int getItemCount() {
        return farmList.size();
    }

    public class FarmViewHolder extends RecyclerView.ViewHolder {
        private ItemFarmerBinding binding;
        public FarmViewHolder(ItemFarmerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Farmer model,int position) {
            Uri uri = Uri.parse(model.getPicture());
            if (uri != null){
                binding.profileImage.setImageURI(uri);
            }

            binding.name.setText(model.getName());
            binding.phoneNumber.setText(model.getPhoneNumber());

            binding.getRoot().setOnClickListener(view -> clickListner.onItemClick(model,position));
        }


    }

    public interface ClickListner {
        void onItemClick(Farmer model, int position);
    }

}
