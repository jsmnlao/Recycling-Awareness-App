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

    public MyAdapter(List<Anime> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
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

        public ViewHolder(RowLayoutBinding binding) {
            super(binding.getRoot());
            this.iconView = binding.icon;
            this.nameView = binding.name;
        }

    }
}