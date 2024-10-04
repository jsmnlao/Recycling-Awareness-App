package edu.sjsu.android.project3jasminelao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.android.project3jasminelao.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    private Anime anime = new Anime(R.drawable.anime2, R.string.a1_name, R.string.a1_des); //placeholder to see if code works


    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
        binding.image.setImageResource(anime.getImageID());
        binding.name.setText(anime.getName());
        binding.detail.setText(anime.getDescription());
        return binding.getRoot();
    }
}