package edu.sjsu.android.recyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class StartNavFragment extends Fragment {

    public StartNavFragment() {
        // Required empty public constructor
    }

    public static StartNavFragment newInstance(String param1, String param2) {
        StartNavFragment fragment = new StartNavFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // Set HomepageFragment as the default fragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomepageFragment())
                    .commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_startnav, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

//        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomepageFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new HomepageFragment();
            } else if (itemId == R.id.nav_community) {
                selectedFragment = new CommunityFragment();
            } else if (itemId == R.id.nav_scan) {
                selectedFragment = new ScanFragment();
            } else if (itemId == R.id.nav_info_hub) {
                selectedFragment = new InfoFragment();
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        });
        return view;
    }
}