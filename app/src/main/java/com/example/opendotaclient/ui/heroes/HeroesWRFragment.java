package com.example.opendotaclient.ui.heroes;

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
import com.example.opendotaclient.ui.adapter.HeroAdapter;
import com.example.opendotaclient.ui.model.HeroModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HeroesWRFragment extends Fragment {

    public HeroesWRFragment(){
           //Required empty
    }

    HashMap<Integer, String> heroes = new HashMap<>();

    private RecyclerView recyclerView;
    ArrayList<HeroModel> heroModelArrayList;
    HeroAdapter heroAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heroeswr, container, false);

        init();
        Long player_id = getActivity().getIntent().getLongExtra("player_id", 339941742);;
        heroModelArrayList = new ArrayList<HeroModel>();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%d/heroes", player_id);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < 20; i++) {
                                JSONObject hero = response.getJSONObject(i);
                                Integer hero_id = hero.getInt("hero_id");
                                Long last_played = hero.getLong("last_played");
                                Integer games = hero.getInt("games");
                                Integer win = hero.getInt("win");

                                heroModelArrayList.add(new HeroModel(heroes.get(hero_id), getEndedTime(last_played), games, getWinRate(games, win)));


                                recyclerView = view.findViewById(R.id.recent_heroes);
                                recyclerView.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                heroAdapter = new HeroAdapter(getContext(), heroModelArrayList);
                                recyclerView.setAdapter(heroAdapter);

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

    private void init() {
        heroes.put(1, "Anti-Mage");
        heroes.put(2, "Axe");
        heroes.put(3, "Bane");
        heroes.put(4, "Bloodseeker");
        heroes.put(5, "Crystal Maiden");
        heroes.put(6, "Drow Ranger");
        heroes.put(7, "Earthshaker");
        heroes.put(8, "Juggernaut");
        heroes.put(9, "Mirana");
        heroes.put(10, "Morphling");
        heroes.put(11, "Shadow Fiend");
        heroes.put(12, "Phantom Lancer");
        heroes.put(13, "Puck");
        heroes.put(14, "Pudge");
        heroes.put(15, "Razor");
        heroes.put(16, "Sand King");
        heroes.put(17, "Storm Spirit");
        heroes.put(18, "Sven");
        heroes.put(19, "Tiny");
        heroes.put(20, "Vengeful Spirit");
        heroes.put(21, "Windranger");
        heroes.put(22, "Zeus");
        heroes.put(23, "Kunkka");
        heroes.put(25, "Lina");
        heroes.put(26, "Lion");
        heroes.put(27, "Shadow Shaman");
        heroes.put(28, "Slardar");
        heroes.put(29, "Tidehunter");
        heroes.put(30, "Witch Doctor");
        heroes.put(31, "Lich");
        heroes.put(32, "Riki");
        heroes.put(33, "Enigma");
        heroes.put(34, "Tinker");
        heroes.put(35, "Sniper");
        heroes.put(36, "Necrophos");
        heroes.put(37, "Warlock");
        heroes.put(38, "Beastmaster");
        heroes.put(39, "Queen of Pain");
        heroes.put(40, "Venomancer");
        heroes.put(41, "Faceless Void");
        heroes.put(42, "Wraith King");
        heroes.put(43, "Death Prophet");
        heroes.put(44, "Phantom Assassin");
        heroes.put(45, "Pugna");
        heroes.put(46, "Templar Assassin");
        heroes.put(47, "Viper");
        heroes.put(48, "Luna");
        heroes.put(49, "Dragon Knight");
        heroes.put(50, "Dazzle");
        heroes.put(51, "Clockwerk");
        heroes.put(52, "Leshrac");
        heroes.put(53, "Nature's Prophet");
        heroes.put(54, "Lifestealer");
        heroes.put(55, "Dark Seer");
        heroes.put(56, "Clinkz");
        heroes.put(57, "Omniknight");
        heroes.put(58, "Enchantress");
        heroes.put(59, "Huskar");
        heroes.put(60, "Night Stalker");
        heroes.put(61, "Broodmother");
        heroes.put(62, "Bounty Hunter");
        heroes.put(63, "Weaver");
        heroes.put(64, "Jakiro");
        heroes.put(65, "Batrider");
        heroes.put(66, "Chen");
        heroes.put(67, "Spectre");
        heroes.put(68, "Ancient Apparition");
        heroes.put(69, "Doom");
        heroes.put(70, "Ursa");
        heroes.put(71, "Spirit Breaker");
        heroes.put(72, "Gyrocopter");
        heroes.put(73, "Alchemist");
        heroes.put(74, "Invoker");
        heroes.put(75, "Silencer");
        heroes.put(76, "Outworld Devourer");
        heroes.put(77, "Lycan");
        heroes.put(78, "Brewmaster");
        heroes.put(79, "Shadow Demon");
        heroes.put(80, "Lone Druid");
        heroes.put(81, "Chaos Knight");
        heroes.put(82, "Meepo");
        heroes.put(83, "Treant Protector");
        heroes.put(84, "Ogre Magi");
        heroes.put(85, "Undying");
        heroes.put(86, "Rubick");
        heroes.put(87, "Disruptor");
        heroes.put(88, "Nyx Assassin");
        heroes.put(89, "Naga Siren");
        heroes.put(90, "Keeper of the Light");
        heroes.put(91, "Io");
        heroes.put(92, "Visage");
        heroes.put(93, "Slark");
        heroes.put(94, "Medusa");
        heroes.put(95, "Troll Warlord");
        heroes.put(96, "Centaur Warrunner");
        heroes.put(97, "Magnus");
        heroes.put(98, "Timbersaw");
        heroes.put(99, "Bristleback");
        heroes.put(100, "Tusk");
        heroes.put(101, "Skywrath Mage");
        heroes.put(102, "Abaddon");
        heroes.put(103, "Elder Titan");
        heroes.put(104, "Legion Commander");
        heroes.put(105, "Techies");
        heroes.put(106, "Ember Spirit");
        heroes.put(107, "Earth Spirit");
        heroes.put(108, "Underlord");
        heroes.put(109, "Terrorblade");
        heroes.put(110, "Phoenix");
        heroes.put(111, "Oracle");
        heroes.put(112, "Winter Wyvern");
        heroes.put(113, "Arc Warden");
        heroes.put(114, "Monkey King");
        heroes.put(119, "Dark Willow");
        heroes.put(120, "Pangolier");
        heroes.put(121, "Grimstroke");
        heroes.put(123, "Hoodwink");
        heroes.put(124, "Anti-Mage");
        heroes.put(125, "Anti-Mage");
        heroes.put(126, "Void Spirit");
        heroes.put(128, "Snapfire");
        heroes.put(129, "Mars");
        heroes.put(135, "Dawnbreaker");
        heroes.put(136, "Marci");
    }

}