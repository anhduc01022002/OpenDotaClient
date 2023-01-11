package com.example.opendotaclient.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import com.example.opendotaclient.ui.adapter.PeerAdapter;
import com.example.opendotaclient.ui.model.PeerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PeersFragment extends Fragment {

    public PeersFragment() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    ArrayList<PeerModel> peerModelArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peers, container, false);

        Long player_id = getActivity().getIntent().getLongExtra("player_id", 392565237);;
        peerModelArrayList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%d/peers", player_id);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < 20; i++) {
                                JSONObject peer = response.getJSONObject(i);
                                String name = peer.getString("personaname");
                                Integer games = peer.getInt("games");
                                Integer wins = peer.getInt("win");
                                Long last_played = peer.getLong("last_played");

                                peerModelArrayList.add(new PeerModel(name, games, getWinRate(games, wins), getEndedTime(last_played)));

                                recyclerView = view.findViewById(R.id.recent_peers);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

                                recyclerView.setAdapter(new PeerAdapter(getContext(), peerModelArrayList));
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


        return view;
    }

    @NonNull
    private String getEndedTime(Long start_time) {
        Long now = System.currentTimeMillis() / 1000L;
        Long ended = (now - start_time);

        if (ended / 31536000 == 1) {
            return String.format("%d year ago", ended/31536000);
        } else if (ended / 31536000 > 1) {
            return String.format("%d years ago", ended/31536000);
        } else if (ended / 2592000 == 1) {
            return String.format("%d month ago", ended/2592000);
        } else if (ended / 2592000 > 1) {
            return String.format("%d months ago", ended/2592000);
        } else if (ended / 604800 == 1) {
            return String.format("%d week ago", ended/604800);
        } else if (ended / 604800 > 1) {
            return String.format("%d weeks ago", ended/604800);
        } else if (ended / 86400 == 1) {
            return String.format("%d day ago", ended/86400);
        } else if (ended / 86400 > 1) {
            return String.format("%d days ago", ended/86400);
        } else if (ended / 3600 == 1) {
            return String.format("%d hour ago", ended/3600);
        } else if (ended / 3600 > 1) {
            return String.format("%d hours ago", ended/3600);
        } else if (ended / 60 == 1) {
            return String.format("%d minute ago", ended/60);
        } else if (ended / 60 > 1) {
            return String.format("%d minutes ago", ended/60);
        } else {
            return String.format("%d seconds ago", ended);
        }
    }

    private String getWinRate(Integer games, Integer wins) {
        Double game = Double.valueOf(games);
        Double win = Double.valueOf(wins);
        Double win_rate = win*100/game;
        return String.format("%.2f%%", win_rate);
    }

}