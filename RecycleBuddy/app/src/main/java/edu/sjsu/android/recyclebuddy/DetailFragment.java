package edu.sjsu.android.recyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.android.recyclebuddy.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    private InfoTopic infoTopic = new InfoTopic(R.string.topic1_name, R.string.topic1_description);

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        if(argument != null){
            String key = getString(R.string.argument_key);
            infoTopic = argument.getParcelable(key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
        binding.name.setText(infoTopic.getName());
        return binding.getRoot();
    }

}