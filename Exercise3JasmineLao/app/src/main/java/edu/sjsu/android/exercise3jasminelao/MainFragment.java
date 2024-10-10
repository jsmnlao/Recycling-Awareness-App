package edu.sjsu.android.exercise3jasminelao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {
    private String data;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            data = getArguments().getString("myText");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // set result TextView to the received data
        ((TextView) view.findViewById(R.id.result)).setText(data);

        // initialize the NavController
        NavController controller = NavHostFragment.findNavController(this);
        // when "get text" button is clicked, navigate to textFragment
        view.findViewById(R.id.get).setOnClickListener(v ->
                controller.navigate(R.id.action_mainFragment_to_textFragment));
        // when "web browser" button is clicked, call onClick
        view.findViewById(R.id.implicit).setOnClickListener(this::onClick);
        return view;

    }

    public void onClick(View view){
        // let data be Amazon's homepage
        Uri website = Uri.parse("http://www.amazon.com");
        // create an implicit Intent with a specific action (view) and the data
        Intent implicit =  new Intent(Intent.ACTION_VIEW, website);
        // create a chooser for the implicit Intent so that user can choose the app to perform action
        Intent choose = Intent.createChooser(implicit, "Load Amazon.com with: ");
        // use resultLauncher from MainActivity to start the Intent to get result back
        MainActivity.resultLauncher.launch(choose); // demonstrates how to explicitly set an app chooser
    }
}