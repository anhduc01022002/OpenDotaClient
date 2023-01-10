package com.example.opendotaclient.ui.heroes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.adapter.ItemRankedListAdapter;
import com.example.opendotaclient.ui.stats.CustomApdater;
import com.example.opendotaclient.ui.stats.RankObject;

import java.util.ArrayList;
import java.util.List;

public class HeroesFragment extends Fragment {
    private ItemRankedListAdapter adapter;
    private Spinner spinner;
    private boolean status = false;
    private RecyclerView heroRankedList;
    // Lay du lieu tu API truyen vao danh sach heroRankedList
    public List<HeroRanked> heroRankedLst = new ArrayList<>();
    public List<HeroRanked> heroRankedLstUpload = new ArrayList<>();
    public List<RankObject> rankObjectList = new ArrayList<>();
    public HeroesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        LayoutInflater localInflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.fragment_heroes, container, false);
        spinner = mView.findViewById(R.id.spinner);
        heroRankedList = mView.findViewById(R.id.HeroRankedList);
        unit(mView, false);
//        heroRankedList = mView.findViewById(R.id.HeroRankedList);
        // Anh xa map layout voi code
        CustomApdater adapter = new CustomApdater(getActivity(),
                R.layout.spinner_item_layout_resource,
                R.id.textView_item_icon,
                R.id.textView_item_name,
                rankObjectList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return mView;

    }

    @SuppressLint("NotifyDataSetChanged")
    private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
        Adapter adapterRank = parent.getAdapter();
        RankObject rankObject = (RankObject) adapterRank.getItem(position);
        heroRankedLstUpload = new ArrayList<>();
        //xu ly sau khi chon rank
        String rank = rankObject.getRank_name();
        for (HeroRanked heroRanked: heroRankedLst){
            if (rank.equals(heroRanked.getRank())){
                heroRankedLstUpload.add(heroRanked);
            }
        }
        if (heroRankedLstUpload != null && heroRankedLstUpload.size() > 0) {
            unit(view, true);
            Toast.makeText(getActivity(), "Selected " + rankObject.getRank_name(), Toast.LENGTH_SHORT).show();
        } else {
            adapter = new ItemRankedListAdapter(getActivity(), heroRankedLstUpload);
            heroRankedList.setAdapter(adapter);
            // thay doi list khi du lieu thay doi
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void unit(View view, boolean status) {
        // set danh sach hero
        if (status) {

//            RecyclerView heroRankedList = view.findViewById(R.id.heroRankedList);
            // Khoi tao layout
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            heroRankedList.setLayoutManager(layoutManager);
            // Truyen tham so vào adapter
            adapter = new ItemRankedListAdapter(getActivity(), heroRankedLstUpload);
            heroRankedList.setAdapter(adapter);
            // thay doi list khi du lieu thay doi
            adapter.notifyDataSetChanged();

        } else {



            HeroRanked hero1 = new HeroRanked("Divine", "Abaddon", "50.00%", "20.08%");
            HeroRanked hero2 = new HeroRanked("Divine", "Zeus", "40.00%", "30.08%");
            HeroRanked hero3 = new HeroRanked("Legend", "Lina", "20.00%", "20.08%");
            HeroRanked hero4 = new HeroRanked("Divine", "Mars", "30.00%", "50.08%");
            HeroRanked hero5 = new HeroRanked("Legend", "Marci", "80.00%", "60.08%");
            heroRankedLst.add(hero1);
            heroRankedLst.add(hero2);
            heroRankedLst.add(hero3);
            heroRankedLst.add(hero4);
            heroRankedLst.add(hero5);

            // set danh sach rank
            RankObject rankObject0 = new RankObject(0, "dire_logo", "Herald");
            RankObject rankObject1 = new RankObject(1, "dire_logo", "Guardian");
            RankObject rankObject2 = new RankObject(2, "dire_logo", "Crusader");
            RankObject rankObject3 = new RankObject(3, "dire_logo", "Archon");
            RankObject rankObject4 = new RankObject(4, "dire_logo", "Legend");
            RankObject rankObject5 = new RankObject(5, "dire_logo", "Divine");
            rankObjectList.add(rankObject0);
            rankObjectList.add(rankObject1);
            rankObjectList.add(rankObject2);
            rankObjectList.add(rankObject3);
            rankObjectList.add(rankObject4);
            rankObjectList.add(rankObject5);

            // Anh xa map layout voi code
//            RecyclerView heroRankedList = view.findViewById(R.id.heroRankedList);
            // Khoi tao layout
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            heroRankedList.setLayoutManager(layoutManager);
            // Truyen tham so vào adapter
            adapter = new ItemRankedListAdapter(getContext(), heroRankedLst);
            heroRankedList.setAdapter(adapter);
            // thay doi list khi du lieu thay doi
            adapter.notifyDataSetChanged();
        }
    }
}