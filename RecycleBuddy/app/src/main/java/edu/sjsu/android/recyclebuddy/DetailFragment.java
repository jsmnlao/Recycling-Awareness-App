package edu.sjsu.android.recyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            infoTopic = getArguments().getParcelable("argument_key");
//            String key = getString(R.string.argument_key);
//            infoTopic = argument.getParcelable(key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
//        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
//        binding.name.setText(infoTopic.getName());
//        binding.description.setText(infoTopic.getDescription());
//        return binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // Use infoTopic to set up your views in detail fragment
        if (infoTopic != null) {
            TextView titleTextView = view.findViewById(R.id.name);
            TextView descriptionTextView = view.findViewById(R.id.description);

            titleTextView.setText(getString(infoTopic.getName()));
            descriptionTextView.setText(getString(infoTopic.getDescription()));
        }
        return view;
    }

}