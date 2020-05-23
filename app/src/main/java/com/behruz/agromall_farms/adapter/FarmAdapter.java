package com.behruz.agromall_farms.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.databinding.ItemFarmBinding;
import com.behruz.agromall_farms.model.Farmer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gideon on 27/08/19.
 */

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.MovieViewHolder> {

    private List<Farmer> timeList;
    private Context context;
    int selected_position = -1;
    private ClickListner clickListner;

    public FarmAdapter(Context context, ClickListner listner) {
        this.context = context;
        timeList = new ArrayList<>();
        this.clickListner = listner;
    }

    private void add(Farmer item) {
        timeList.add(item);
        notifyItemInserted(timeList.size() - 1);
    }

    public void addAll(List<Farmer> movieDatas) {
        for (Farmer movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(Farmer item) {
        int position = timeList.indexOf(item);
        if (position > -1) {
            timeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Farmer getItem(int position) {
        return timeList.get(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFarmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_farm, parent, false);

        final MovieViewHolder movieViewHolder = new MovieViewHolder(binding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final Farmer model = timeList.get(position);
        holder.bind(model,position);
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemFarmBinding binding;
        public MovieViewHolder(ItemFarmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Farmer model,int position) {
 //           String aa = HTTPS+model.getArtistPic();
//            if (aa!= null){
//                Glide.with(context)
//                        .load(aa)
//                        .error(context.getResources().getDrawable(R.drawable.ic_empty_music2))
//                        .into(binding.imageViewProfile);
//            }

            binding.itemTextView.setText(model.getName());
            binding.getRoot().setOnClickListener(view -> clickListner.onItemClick(model,position));
        }


    }

    public interface ClickListner {
        void onItemClick(Farmer model, int position);
    }

}
