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
import com.example.opendotaclient.databinding.FragmentMatchBenchmarkBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchBenchmarkFragment extends Fragment {

    private FragmentMatchBenchmarkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatchBenchmarkBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        fetchData();
        return view;
    }

    private void fetchData() {
        Long match_id = getActivity().getIntent().getLongExtra("match_id", 0);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/matches/%d", match_id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray players = response.getJSONArray("players");

                            JSONObject player_1 = players.getJSONObject(0);
                            binding.matchBenchmarkRadiantPlayer1.setText(player_1.getString("personaname"));
                            binding.matchBenchmarkRadiantGpm1.setText(String.format("%d",player_1.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantXpm1.setText(String.format("%d",player_1.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantKpm1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantLhpm1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHdpm1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHhpm1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantTd1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkRadiantSpm1.setText(String.format("%.2f",player_1.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));


                            JSONObject player_2 = players.getJSONObject(1);
                            binding.matchBenchmarkRadiantPlayer2.setText(player_2.getString("personaname"));
                            binding.matchBenchmarkRadiantGpm2.setText(String.format("%d",player_2.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantXpm2.setText(String.format("%d",player_2.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantKpm2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantLhpm2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHdpm2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHhpm2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantTd2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkRadiantSpm2.setText(String.format("%.2f",player_2.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_3 = players.getJSONObject(2);
                            binding.matchBenchmarkRadiantPlayer3.setText(player_3.getString("personaname"));
                            binding.matchBenchmarkRadiantGpm3.setText(String.format("%d",player_3.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantXpm3.setText(String.format("%d",player_3.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantKpm3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantLhpm3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHdpm3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHhpm3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantTd3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkRadiantSpm3.setText(String.format("%.2f",player_3.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_4 = players.getJSONObject(3);
                            binding.matchBenchmarkRadiantPlayer4.setText(player_4.getString("personaname"));
                            binding.matchBenchmarkRadiantGpm4.setText(String.format("%d",player_4.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantXpm4.setText(String.format("%d",player_4.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantKpm4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantLhpm4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHdpm4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHhpm4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantTd4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkRadiantSpm4.setText(String.format("%.2f",player_4.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_5 = players.getJSONObject(4);
                            binding.matchBenchmarkRadiantPlayer5.setText(player_5.getString("personaname"));
                            binding.matchBenchmarkRadiantGpm5.setText(String.format("%d",player_5.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantXpm5.setText(String.format("%d",player_5.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkRadiantKpm5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantLhpm5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHdpm5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantHhpm5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkRadiantTd5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkRadiantSpm5.setText(String.format("%.2f",player_5.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_6 = players.getJSONObject(5);
                            binding.matchBenchmarkDirePlayer1.setText(player_6.getString("personaname"));
                            binding.matchBenchmarkDireGpm1.setText(String.format("%d",player_6.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkDireXpm1.setText(String.format("%d",player_6.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkDireKpm1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireLhpm1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHdpm1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHhpm1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireTd1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkDireSpm1.setText(String.format("%.2f",player_6.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_7 = players.getJSONObject(6);
                            binding.matchBenchmarkDirePlayer2.setText(player_7.getString("personaname"));
                            binding.matchBenchmarkDireGpm2.setText(String.format("%d",player_7.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkDireXpm2.setText(String.format("%d",player_7.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkDireKpm2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireLhpm2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHdpm2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHhpm2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireTd2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkDireSpm2.setText(String.format("%.2f",player_7.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_8 = players.getJSONObject(7);
                            binding.matchBenchmarkDirePlayer3.setText(player_8.getString("personaname"));
                            binding.matchBenchmarkDireGpm3.setText(String.format("%d",player_8.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkDireXpm3.setText(String.format("%d",player_8.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkDireKpm3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireLhpm3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHdpm3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHhpm3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireTd3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkDireSpm3.setText(String.format("%.2f",player_8.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_9 = players.getJSONObject(8);
                            binding.matchBenchmarkDirePlayer4.setText(player_9.getString("personaname"));
                            binding.matchBenchmarkDireGpm4.setText(String.format("%d",player_9.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkDireXpm4.setText(String.format("%d",player_9.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkDireKpm4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireLhpm4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHdpm4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHhpm4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireTd4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkDireSpm4.setText(String.format("%.2f",player_9.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

                            JSONObject player_10 = players.getJSONObject(9);
                            binding.matchBenchmarkDirePlayer5.setText(player_10.getString("personaname"));
                            binding.matchBenchmarkDireGpm5.setText(String.format("%d",player_10.getJSONObject("benchmarks").getJSONObject("gold_per_min").getInt("raw")));
                            binding.matchBenchmarkDireXpm5.setText(String.format("%d",player_10.getJSONObject("benchmarks").getJSONObject("xp_per_min").getInt("raw")));
                            binding.matchBenchmarkDireKpm5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("kills_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireLhpm5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("last_hits_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHdpm5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("hero_damage_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireHhpm5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("hero_healing_per_min").getDouble("raw")));
                            binding.matchBenchmarkDireTd5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("tower_damage").getDouble("raw")));
                            binding.matchBenchmarkDireSpm5.setText(String.format("%.2f",player_10.getJSONObject("benchmarks").getJSONObject("stuns_per_min").getDouble("raw")));

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}