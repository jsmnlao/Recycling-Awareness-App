package edu.sjsu.android.recyclebuddy;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sjsu.android.recyclebuddy.databinding.RowLayoutBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<InfoTopic> infoTopicList;
    private OnInfoTopicClickedListener listener;

    public MyAdapter(List<InfoTopic> infoTopicList) {
        this.infoTopicList = infoTopicList;
    }

    public void setListener(OnInfoTopicClickedListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return infoTopicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView;

        public ViewHolder(RowLayoutBinding binding, OnInfoTopicClickedListener listener) {
            super(binding.getRoot());
            this.nameView = binding.name;
            binding.getRoot().setOnClickListener(v -> listener.onClick(getLayoutPosition())); // call onclick method and get position with layout position method
            // so adapter knows which row is being clicked based on position id
        }
    }
}
