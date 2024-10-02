package edu.sjsu.android.exercise2jasminelao;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.sjsu.android.exercise2jasminelao.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<String> mValues;

    public MyAdapter(List<String> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final TextView mIdView;
//        public final TextView mContentView;
        public String mItem;
        protected final FragmentItemBinding binding;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // The root represent one row and when row is clicked, a toast will popup
            this.binding.getRoot().setOnClickListener(view ->
                    Toast.makeText(view.getContext(), "A row clicked",
                            Toast.LENGTH_SHORT).show());
//            mIdView = binding.itemNumber;
//            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}