package edu.sjsu.android.recyclebuddy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.sjsu.android.recyclebuddy.databinding.RowLayoutBinding;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private final List<Topic> topicList;
    private OnTopicClickedListener listener;

    public InfoAdapter(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public void setListener(OnTopicClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutBinding binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, listener); // new view holder passing binder and listener

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic coffee = topicList.get(position); //mValues is arraylist, position is index, returns obj from list
        holder.nameView.setText(coffee.getName());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView;

        public ViewHolder(RowLayoutBinding binding, OnTopicClickedListener listener) {
            super(binding.getRoot());
            this.nameView = binding.name;
            binding.getRoot().setOnClickListener(v -> listener.onClick(getLayoutPosition())); // call onclick method and get position with layout position method
        }
    }
}
