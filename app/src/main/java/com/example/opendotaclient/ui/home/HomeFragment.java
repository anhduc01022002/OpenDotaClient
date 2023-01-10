package com.example.opendotaclient.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.heroes.HeroesWRFragment;
import com.example.opendotaclient.ui.stats.MatchesFragment;
import com.example.opendotaclient.ui.stats.OverviewFragment;
import com.example.opendotaclient.ui.stats.PeersFragment;
import com.example.opendotaclient.ui.stats.ViewPagerAdapter;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.activity_home, parent, false);
        AddViewPager(view);
        return view;
    }

    private void AddViewPager(View view) {
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        // setting up the adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new OverviewFragment(), "Overview");
        viewPagerAdapter.add(new MatchesFragment(), "Matches");
        viewPagerAdapter.add(new HeroesWRFragment(), "Heroes");
        viewPagerAdapter.add(new PeersFragment(), "Peers");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);
    }
}