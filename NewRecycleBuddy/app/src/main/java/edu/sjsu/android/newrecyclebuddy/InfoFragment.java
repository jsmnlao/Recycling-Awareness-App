package edu.sjsu.android.newrecyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InfoFragment extends Fragment {
    private ArrayList<InfoTopic> infoTopicList;


    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoTopicList = new ArrayList<>();
        infoTopicList.add(new InfoTopic(R.string.topic1_name, R.string.topic1_description, R.drawable.recyclable_material_types));
        infoTopicList.add(new InfoTopic(R.string.topic2_name, R.string.topic2_description, R.drawable.triangle_recycle_codes));
        infoTopicList.add(new InfoTopic(R.string.topic3_name, R.string.topic3_description, R.drawable.recycling_process));
        infoTopicList.add(new InfoTopic(R.string.topic4_name, R.string.topic4_description, R.drawable.environmental_impacts));
        infoTopicList.add(new InfoTopic(R.string.topic5_name, R.string.topic5_description, R.drawable.recycling_facts));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "clicked recyclebuddy/InfoFragment");
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // set the adapter
        MyAdapter adapter = new MyAdapter(infoTopicList);
        adapter.setListener(this::onClick);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void onClick(int position){
        Log.d("test", "clicked recyclebuddy/InfoFragment onClick method");
        goDetail(position);
    }

    public void goDetail(int position){
        Log.d("test", "clicked recyclebuddy/InfoFragment goDetail method");
        InfoTopic infoTopic = infoTopicList.get(position);
        Log.d("test", "infoTopic is: " + infoTopic);

        Bundle bundle = new Bundle();
        bundle.putParcelable("argument_key", infoTopic);

        try {
            NavController controller = NavHostFragment.findNavController(this);
            NavDestination currentDestination = controller.getCurrentDestination();
            controller.navigate(R.id.action_nav_info_hub_to_detailFragment, bundle);

            if (currentDestination != null) {
                Log.d("test", "InfoFragment Current Destination: " + currentDestination.getLabel());
            } else {
                Log.d("test", "InfoFragment: No current destination");
            }
        } catch (Exception e) {
            Log.e("test", "Failed to navigate to detail fragment", e);
        }
        Log.d("test", "clicked recyclebuddy/InfoFragment END of goDetail method");
    }
}