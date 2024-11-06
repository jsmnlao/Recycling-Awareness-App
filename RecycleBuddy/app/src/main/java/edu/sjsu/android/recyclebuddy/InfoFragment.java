package edu.sjsu.android.recyclebuddy;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InfoFragment extends Fragment {
    private ArrayList<Topic> topicList;


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
        topicList = new ArrayList<>();
        topicList.add(new Topic(String.valueOf(R.string.topic1_name)));
        topicList.add(new Topic(String.valueOf(R.string.topic2_name)));
        topicList.add(new Topic(String.valueOf(R.string.topic3_name)));
        topicList.add(new Topic(String.valueOf(R.string.topic4_name)));
        topicList.add(new Topic(String.valueOf(R.string.topic5_name)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic("Recycling Plastics", "Learn about recycling different types of plastics."));
        topicList.add(new Topic("Recycling Metals", "How to recycle metals responsibly."));
        // Add more topics as needed

        InfoAdapter adapter = new InfoAdapter(topicList);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_info, container, false);
//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            InfoAdapter adapter = new InfoAdapter(topicList); // adapter constructed with animeList
////            adapter.setListener(this::onClick); // onclick method through interface
//            recyclerView.setAdapter(adapter);
//        }
//        return view;
    }

//    public void onClick(int position){
//        goDetail(position);
//    }

//    public void goDetail(int position){
//        Topic coffee = topicList.get(position); // array list method -- get coffee obj from position
//        Bundle bundle = new Bundle();
//
//        bundle.putParcelable(requireContext().getString(R.string.argument_key), coffee); // calling coffee to bundle obj --> mapping terminologies
//        NavController controller = NavHostFragment.findNavController(this); // this = this fragment
//        controller.navigate(R.id.list_to_detail, bundle); // id of the action wrapped with bundle
//    }
}