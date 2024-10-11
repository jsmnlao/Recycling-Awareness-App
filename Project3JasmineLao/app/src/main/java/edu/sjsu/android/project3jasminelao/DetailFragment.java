package edu.sjsu.android.project3jasminelao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.android.project3jasminelao.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    private Coffee coffee = new Coffee(R.drawable.coffee1, R.string.a1_name, R.string.a1_des); //placeholder to see if code works

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        if (argument != null) {
            String key = getString(R.string.argument_key);
            coffee = argument.getParcelable(key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
        binding.image.setImageResource(coffee.getImageID());
        binding.name.setText(coffee.getName());
        binding.detail.setText(coffee.getDescription());
        binding.detail.setMovementMethod(new ScrollingMovementMethod());
        return binding.getRoot();
    }
}