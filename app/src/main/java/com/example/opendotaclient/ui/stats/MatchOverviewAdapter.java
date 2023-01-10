package com.example.opendotaclient.ui.stats;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MatchOverviewAdapter extends FragmentStateAdapter {

    public MatchOverviewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            default:
                return new MatchOverviewFragment();
            case 0:
                return new MatchOverviewFragment();
            case 1:
                return new MatchBenchmarkFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
