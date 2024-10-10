package edu.sjsu.android.project3jasminelao;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import edu.sjsu.android.project3jasminelao.databinding.RowLayoutBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Anime> animeList;
    private OnAnimeClickedListener listener;

    public MyAdapter(List<Anime> animeList) {
        this.animeList = animeList;
    }

    public void setListener(OnAnimeClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutBinding binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, listener); // new view holder passing binder and listener

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Anime anime = animeList.get(position); //mValues is arraylist, position is index, returns obj from list
        holder.iconView.setImageResource(anime.getImageID());
        holder.nameView.setText(anime.getName());
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView iconView;
        public final TextView nameView;

        public ViewHolder(RowLayoutBinding binding, OnAnimeClickedListener listener) {
            super(binding.getRoot());
            this.iconView = binding.icon;
            this.nameView = binding.name;
            binding.getRoot().setOnClickListener(v -> listener.onClick(getLayoutPosition())); // call onclick method and get position with layout position method
            // so adapter knows which row is being clicked based on position id
        }
    }
}