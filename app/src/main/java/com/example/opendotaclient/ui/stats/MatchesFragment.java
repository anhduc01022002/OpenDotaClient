package com.example.opendotaclient.ui.stats;

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
import com.example.opendotaclient.ui.adapter.MatchAdapter;
import com.example.opendotaclient.ui.model.MatchModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    public MatchesFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MatchesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<MatchModel> matchList;
    private MatchAdapter matchAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        Long player_id = getActivity().getIntent().getLongExtra("player_id", 446737104);;
        matchList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%d/matches", player_id);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < 20; i++) {
                                JSONObject match = response.getJSONObject(i);
                                Long match_id = match.getLong("match_id");

                                Integer duration = match.getInt("duration");
                                Integer game_mode = match.getInt("game_mode");
                                Integer hero_id = match.getInt("hero_id");
                                Integer kills = match.getInt("kills");
                                Integer deaths = match.getInt("deaths");
                                Integer assists = match.getInt("assists");
                                Long start_time = match.getLong("start_time");

                                matchList.add(new MatchModel(match_id, R.drawable.dire_logo, getMode(game_mode), getKda(kills, deaths, assists), getTime(duration), getEndedTime(start_time, duration)));

                                matchAdapter = new MatchAdapter(getContext(), matchList);
                                recyclerView = view.findViewById(R.id.recentmatch);
                                recyclerView.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(matchAdapter);
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

    private String getMode(Integer mode_id) {
        if (mode_id == 22) {
            return "All Draft";
        } else if (mode_id == 2) {
            return "Captain Mode";
        } else {
            return mode_id.toString();
        }
    }

    private String getTime(Integer time) {
        int h, m, s;

        h = time/3600;
        time %= 3600;
        m = time/60;
        time %= 60;
        s = time;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private String getKda(Integer k, Integer d, Integer a) {
        return String.format("%d/%d/%d", k, d, a);
    }

    private String getEndedTime(Long start_time, Integer duration) {
        Long now = System.currentTimeMillis() / 1000L;
        Long ended = (now - start_time - duration);

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

}