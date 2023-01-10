package com.example.opendotaclient.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.opendotaclient.R;
import com.example.opendotaclient.databinding.FragmentMatchOverviewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchOverviewFragment extends Fragment {

    private Long match_id;


    private FragmentMatchOverviewBinding binding;

    public MatchOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatchOverviewBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        fetchData();
        return view;
    }

    private void fetchData() {
        match_id = getActivity().getIntent().getLongExtra("match_id", 0);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/matches/%d", match_id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean radiant_win = response.getBoolean("radiant_win");
                            String match_id = response.getString("match_id");
                            Integer region = response.getInt("region");
                            Integer radiant_score = response.getInt("radiant_score");
                            Integer dire_score = response.getInt("dire_score");
                            Integer game_mode = response.getInt("game_mode");
                            Integer duration = response.getInt("duration");
                            Long start_time = response.getLong("start_time");
                            String radiant_team_name = "RADIANT";
                            String dire_team_name = "DIRE";
                            if (game_mode == 2) {
                                radiant_team_name = response.getJSONObject("radiant_team").getString("name");
                                dire_team_name = response.getJSONObject("dire_team").getString("name");
                            }
                            JSONArray players = response.getJSONArray("players");

                            if (radiant_win) {
                                binding.matchOverviewVictoryTeam.setText("Radiant Victory");
                                binding.matchOverviewVictoryTeamIcon.setImageResource(R.drawable.radiant_logo);
                            } else {
                                binding.matchOverviewVictoryTeam.setText(("Dire Victory"));
                                binding.matchOverviewVictoryTeamIcon.setImageResource(R.drawable.dire_logo);
                            }
                            binding.matchOverviewMatchId.setText(match_id);
                            binding.matchOverviewRegion.setText(region.toString());
                            binding.matchOverviewDireKills.setText(dire_score.toString());
                            binding.matchOverviewRadiantKills.setText(radiant_score.toString());
                            binding.matchOverviewGameMode.setText(getMode(game_mode));
                            binding.matchOverviewDuration.setText(getTime(duration));
                            binding.matchOverviewEndTime.setText(getEndedTime(start_time, duration));
                            binding.matchOverviewRadiantTeamName.setText(radiant_team_name);
                            binding.matchOverviewDireTeamName.setText(dire_team_name);

                            JSONObject player_1 = players.getJSONObject(0);
                            binding.matchOverviewRadiantPlayer1.setText(player_1.getString("personaname"));
                            binding.matchOverviewRadiantKda1.setText(String.format("%d/%d/%d", player_1.getInt("kills"), player_1.getInt("deaths"), player_1.getInt("assists")));

                            JSONObject player_2 = players.getJSONObject(1);
                            binding.matchOverviewRadiantPlayer2.setText(player_2.getString("personaname"));
                            binding.matchOverviewRadiantKda2.setText(String.format("%d/%d/%d", player_2.getInt("kills"), player_2.getInt("deaths"), player_2.getInt("assists")));

                            JSONObject player_3 = players.getJSONObject(2);
                            binding.matchOverviewRadiantPlayer3.setText(player_3.getString("personaname"));
                            binding.matchOverviewRadiantKda3.setText(String.format("%d/%d/%d", player_3.getInt("kills"), player_3.getInt("deaths"), player_3.getInt("assists")));

                            JSONObject player_4 = players.getJSONObject(3);
                            binding.matchOverviewRadiantPlayer4.setText(player_4.getString("personaname"));
                            binding.matchOverviewRadiantKda4.setText(String.format("%d/%d/%d", player_4.getInt("kills"), player_4.getInt("deaths"), player_4.getInt("assists")));

                            JSONObject player_5 = players.getJSONObject(4);
                            binding.matchOverviewRadiantPlayer5.setText(player_5.getString("personaname"));
                            binding.matchOverviewRadiantKda5.setText(String.format("%d/%d/%d", player_5.getInt("kills"), player_5.getInt("deaths"), player_5.getInt("assists")));

                            JSONObject player_6 = players.getJSONObject(5);
                            binding.matchOverviewDirePlayer1.setText(player_6.getString("personaname"));
                            binding.matchOverviewDireKda1.setText(String.format("%d/%d/%d", player_6.getInt("kills"), player_6.getInt("deaths"), player_6.getInt("assists")));

                            JSONObject player_7 = players.getJSONObject(6);
                            binding.matchOverviewDirePlayer2.setText(player_7.getString("personaname"));
                            binding.matchOverviewDireKda2.setText(String.format("%d/%d/%d", player_7.getInt("kills"), player_7.getInt("deaths"), player_7.getInt("assists")));

                            JSONObject player_8 = players.getJSONObject(7);
                            binding.matchOverviewDirePlayer3.setText(player_8.getString("personaname"));
                            binding.matchOverviewDireKda3.setText(String.format("%d/%d/%d", player_8.getInt("kills"), player_8.getInt("deaths"), player_8.getInt("assists")));

                            JSONObject player_9 = players.getJSONObject(8);
                            binding.matchOverviewDirePlayer4.setText(player_9.getString("personaname"));
                            binding.matchOverviewDireKda4.setText(String.format("%d/%d/%d", player_9.getInt("kills"), player_9.getInt("deaths"), player_9.getInt("assists")));

                            JSONObject player_10 = players.getJSONObject(9);
                            binding.matchOverviewDirePlayer5.setText(player_10.getString("personaname"));
                            binding.matchOverviewDireKda5.setText(String.format("%d/%d/%d", player_10.getInt("kills"), player_10.getInt("deaths"), player_10.getInt("assists")));

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}