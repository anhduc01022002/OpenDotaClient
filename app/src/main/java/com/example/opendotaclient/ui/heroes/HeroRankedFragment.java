package com.example.opendotaclient.ui.heroes;

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

public class HeroRankedFragment extends Fragment {

    private ItemRankedListAdapter adapter;
    public List<HeroRanked> heroRankedLst;

    public HeroRankedFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_hero_ranked, container, false);

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
                                Integer win = hero.getInt("pro_win");
                                Integer pick = hero.getInt("pro_pick");
                                Integer ban = hero.getInt("pro_ban");

                                heroRankedLst.add(new HeroRanked(name, getWinRate(pick, win), ban.toString()));



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

}