package com.example.opendotaclient.ui.heroes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.adapter.ItemRankedListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HeroTurboFragment extends Fragment {

    private ItemRankedListAdapter adapter;
    // Lay du lieu tu API truyen vao danh sach heroRankedList
    public List<HeroRanked> heroRankedLst = new ArrayList<>();

    public HeroTurboFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_hero_turbo, container, false);
        heroRankedLst = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://api.opendota.com/api/heroStats");

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject hero = response.getJSONObject(i);
                                String name = hero.getString("localized_name");
                                Integer win = hero.getInt("turbo_wins");
                                Integer pick = hero.getInt("turbo_picks");

                                heroRankedLst.add(new HeroRanked(name, getWinRate(pick, win), pick.toString()));



                                RecyclerView heroRankedList = mView.findViewById(R.id.HeroRankedList);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                heroRankedList.setLayoutManager(layoutManager);
                                adapter = new ItemRankedListAdapter(getActivity(), heroRankedLst);
                                heroRankedList.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);


        return mView;
    }

    private String getWinRate(Integer games, Integer wins) {
        Double game = Double.valueOf(games);
        Double win = Double.valueOf(wins);
        Double win_rate = win*100/game;
        return String.format("%.2f%%", win_rate);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void unit(View view) {
        HeroRanked hero1 = new HeroRanked("Abadon", "50.00%", "20.08%");
        HeroRanked hero2 = new HeroRanked("Zeus", "40.00%", "30.08%");
        HeroRanked hero3 = new HeroRanked("Lina", "20.00%", "20.08%");
        HeroRanked hero4 = new HeroRanked("Mars", "30.00%", "50.08%");
        HeroRanked hero5 = new HeroRanked("Marci", "80.00%", "60.08%");
        heroRankedLst.add(hero1);
        heroRankedLst.add(hero2);
        heroRankedLst.add(hero3);
        heroRankedLst.add(hero4);
        heroRankedLst.add(hero5);
        // Anh xa map layout voi code
        RecyclerView heroRankedList = view.findViewById(R.id.HeroRankedList);
        // Khoi tao layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        heroRankedList.setLayoutManager(layoutManager);
        // Truyen tham so vào adapter
        adapter = new ItemRankedListAdapter(getActivity(), heroRankedLst);
        heroRankedList.setAdapter(adapter);
        // thay doi list khi du lieu thay doi
    }
}