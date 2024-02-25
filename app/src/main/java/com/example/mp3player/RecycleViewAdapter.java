package com.example.mp3player;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleViewAdapter extends RecyclerView.Adapter< RecycleViewAdapter.ViewHolder>  {

    private ArrayList<String> mData;

    public RecycleViewAdapter(ArrayList<String> data) {
        mData = data;
    }


    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo view cho mỗi item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        // Gán dữ liệu cho view
        holder.tvSongName.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSongName;  // Đã thay đổi tên biến
        ImageView btnDown, btnPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSongName = itemView.findViewById(R.id.txtNameSong);  // Sử dụng tên biến mới
            btnDown = itemView.findViewById(R.id.btnDownload);
            btnPlay = itemView.findViewById(R.id.btnPlay);
        }
    }
}
