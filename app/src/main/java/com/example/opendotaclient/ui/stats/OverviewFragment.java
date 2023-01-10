package com.example.opendotaclient.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.opendotaclient.R;
import com.example.opendotaclient.databinding.FragmentOverviewBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class OverviewFragment extends Fragment {

    private FragmentOverviewBinding binding;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverviewBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        fetchData();
        return view;
    }

    private void fetchData() {
        Long player_id = getActivity().getIntent().getLongExtra("player_id", 446737104);//
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%d", player_id);
        String win_lose_url = String.format("https://opendota.com/api/players/%d/wl", player_id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Integer leaderboard_rank;
                            if (response.isNull("leaderboard_rank")) {
                                leaderboard_rank = 0;
                            } else {
                                leaderboard_rank = response.getInt("leaderboard_rank");
                            }
                            Integer solo_competitive_rank;
                            if (response.isNull("solo_competitive_rank")) {
                                solo_competitive_rank = 0;
                            } else {
                                solo_competitive_rank = response.getInt("solo_competitive_rank");
                            }
                            Integer competitive_rank;
                            if (response.isNull("competitive_rank")) {
                                competitive_rank = 0;
                            } else {
                                competitive_rank = response.getInt("competitive_rank");
                            }
                            String name = response.getJSONObject("profile").getString("personaname");
                            String avatar = response.getJSONObject("profile").getString("avatarfull");
                            String steam_url = response.getJSONObject("profile").getString("profileurl");

                            binding.homeOverviewName.setText(name);
                            binding.leaderboardRank.setText(String.format("#%d",leaderboard_rank));
                            binding.soloCompetitiveRank.setText(String.format("#%d",solo_competitive_rank));
                            binding.competitiveRank.setText(String.format("#%d",competitive_rank));
                            ImageView imageView = getView().findViewById(R.id.home_overview_avatar);
                            Picasso.with(getContext()).load(avatar).into(imageView);
                            binding.homeOverviewSteamUrl.setText(steam_url);

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

        JsonObjectRequest winLoseRequest = new JsonObjectRequest(Request.Method.GET, win_lose_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Integer wins = response.getInt("win");
                            Integer loses = response.getInt("lose");
                            Integer games = wins + loses;

                            binding.homeOverviewWin.setText(wins.toString());
                            binding.homeOverviewGames.setText(games.toString());
                            binding.homeOverviewWinRate.setText(getWinRate(games, wins));

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

        queue.add(winLoseRequest);
        queue.add(jsonObjectRequest);

    }

    private String getWinRate(Integer games, Integer wins) {
        Double game = Double.valueOf(games);
        Double win = Double.valueOf(wins);
        Double win_rate = win*100/game;
        return String.format("%.2f%%", win_rate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}