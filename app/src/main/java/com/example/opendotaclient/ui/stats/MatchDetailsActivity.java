package com.example.opendotaclient.ui.stats;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.opendotaclient.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MatchDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);


        TabLayout match_details_tab_layout = findViewById(R.id.match_details_tab_layout);
        ViewPager2 match_details_view_pager = findViewById(R.id.match_details_view_pager);

        MatchOverviewAdapter overview_adapter = new MatchOverviewAdapter(this);
        match_details_view_pager.setAdapter(overview_adapter);

        new TabLayoutMediator(match_details_tab_layout, match_details_view_pager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Overview");
                    break;
                case 1:
                    tab.setText("Benchmark");
                    break;
            }
        }).attach();

    }
}