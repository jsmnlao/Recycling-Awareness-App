package edu.sjsu.android.recyclebuddy;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        infoTopicList.add(new InfoTopic(R.string.topic1_name, R.string.topic1_description));
        infoTopicList.add(new InfoTopic(R.string.topic2_name, R.string.topic1_description));
        infoTopicList.add(new InfoTopic(R.string.topic3_name, R.string.topic1_description));
        infoTopicList.add(new InfoTopic(R.string.topic4_name, R.string.topic1_description));
        infoTopicList.add(new InfoTopic(R.string.topic5_name, R.string.topic1_description));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        goDetail(position);
    }

    public void goDetail(int position){
        InfoTopic infoTopic = infoTopicList.get(position);
        Bundle bundle = new Bundle();

//        bundle.putParcelable(requireContext().getString(R.string.argument_key), infoTopic); // calling coffee to bundle obj --> mapping terminologies
        bundle.putParcelable("argument_key", infoTopic);
        NavController controller = NavHostFragment.findNavController(this); // this = this fragment
        controller.navigate (R.id.list_to_detail, bundle); // id of the action wrapped with bundle
    }
}