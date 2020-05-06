package com.example.trac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trac.databinding.WelcomeScreenBinding;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {

    private List<WelcomeScreenData> dataList;
    private WelcomeScreenBinding welcomeScreenBinding;

    public ViewPagerAdapter(List<WelcomeScreenData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        welcomeScreenBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.welcome_screen, parent, false);
        return new MyViewHolder(welcomeScreenBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setOnBoardingData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void setOnBoardingData(WelcomeScreenData welcomeScreenData) {
            welcomeScreenBinding.titleView.setText(welcomeScreenData.getTitle());
            welcomeScreenBinding.descriptionView.setText(welcomeScreenData.getDescription());
            welcomeScreenBinding.imageView.setImageResource(welcomeScreenData.getScreenImage());
        }

    }

}
