package com.example.opendotaclient.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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
import com.example.opendotaclient.ui.MainActivity;
import com.example.opendotaclient.ui.SelectListener;
import com.example.opendotaclient.ui.adapter.UserAdapter;
import com.example.opendotaclient.ui.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SelectListener {

    private RecyclerView rcvUsers;
    private UserAdapter userAdapter;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        String player_id = "446737104";
        List<User> peerModelArrayList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%s/peers", player_id);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < 20; i++) {
                                JSONObject peer = response.getJSONObject(i);
                                String name = peer.getString("personaname");
                                Long account_id = peer.getLong("account_id");


                                peerModelArrayList.add(new User(R.drawable.dire_logo, name, account_id));

                                rcvUsers = rootView.findViewById(R.id.rcv_users);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
                                rcvUsers.setLayoutManager(linearLayoutManager);
                                userAdapter = new UserAdapter(peerModelArrayList, getActivity().getBaseContext(), SearchFragment.this::onItemClicked);
                                rcvUsers.setAdapter(userAdapter);

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

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) rootView.findViewById(R.id.search_bar);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return rootView;
    }
    @Override
    public void onItemClicked(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("player_id", rcvUsers.getId());
        startActivity(intent);
    }
}