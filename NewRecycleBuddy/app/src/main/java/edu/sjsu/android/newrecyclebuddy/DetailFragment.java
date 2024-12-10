package edu.sjsu.android.newrecyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    //    private InfoTopic infoTopic = new InfoTopic(R.string.topic1_name, R.string.topic1_description);
    private InfoTopic infoTopic;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private ImageView imageView, backArrow;

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
        Log.d("test", "clicked recyclebuddy/DetailFragment onCreate");
        Bundle argument = getArguments();
        if (argument != null) {
            infoTopic = argument.getParcelable("argument_key");
            if (infoTopic != null) {
                Log.d("test", "Received InfoTopic: " + infoTopic.getName() + ", " + infoTopic.getDescription());
            } else {
                Log.d("test", "InfoTopic is null");
            }
        } else {
            Log.d("test", "Arguments are null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "clicked recyclebuddy/DetailFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        nameTextView = view.findViewById(R.id.topic_name);
        descriptionTextView = view.findViewById(R.id.description);
        imageView = view.findViewById(R.id.image);
        backArrow = view.findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(this::navigateToInfo);

        // Use infoTopic to set up your views in detail fragment
        if (infoTopic != null) {
//            TextView titleTextView = view.findViewById(R.id.topic_name);
//            TextView descriptionTextView = view.findViewById(R.id.description);

            nameTextView.setText(getString(infoTopic.getName()));
            descriptionTextView.setText(getString(infoTopic.getDescription()));
            imageView.setImageResource(infoTopic.getImage());
        }
        else{
            Log.d("test", "InfoTopic is still null in onCreateView");
        }
        return view;
    }

    private void navigateToInfo(View view) {
        NavController controller = Navigation.findNavController(view);
        controller.navigate(R.id.action_detailFragment_to_nav_info_hub);
    }
}