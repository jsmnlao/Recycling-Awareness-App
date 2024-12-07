package edu.sjsu.android.newrecyclebuddy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sjsu.android.newrecyclebuddy.databinding.RowLayoutBinding;

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
        RowLayoutBinding binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        InfoTopic topic = infoTopicList.get(position); //mValues is arraylist, position is index, returns obj from list
        holder.nameView.setText(topic.getName());
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
