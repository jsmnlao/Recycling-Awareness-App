package edu.sjsu.android.project3jasminelao;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/**
 * A fragment representing a list of Items.
 */
public class ListFragment extends Fragment {

    private ArrayList<Anime> animeList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animeList = new ArrayList<>();
        animeList.add(new Anime(R.drawable.anime1, R.string.a1_name, R.string.a1_des));
        animeList.add(new Anime(R.drawable.anime2, R.string.a2_name, R.string.a2_des));
        animeList.add(new Anime(R.drawable.anime3, R.string.a3_name, R.string.a3_des));
        animeList.add(new Anime(R.drawable.anime4, R.string.a4_name, R.string.a4_des));
        animeList.add(new Anime(R.drawable.anime5, R.string.a5_name, R.string.a5_des));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyAdapter(animeList));
        }
        return view;
    }

    public void onClick(int position){
        if(position == animeList.size() - 1){ // if position is last one
            showWarning(position);
        }
        else{
            goDetail(position);
        }

    }


    public void goDetail(int position){
        // TODO: implement navigation
    }

    public void showWarning(int position){
        // TODO: lesson 10, page 18
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Warning");
        builder.setMessage("Warning: ");
        builder.setPositiveButton("Yes", (dialog, id) -> {
            goDetail(position);
        });
        builder.setNegativeButton("No", (dialog, id) -> {
            // do nothing when user clicks no
        });
        builder.create().show();
    }
}