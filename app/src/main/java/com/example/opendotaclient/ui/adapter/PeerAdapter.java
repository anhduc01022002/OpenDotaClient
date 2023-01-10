package com.example.opendotaclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.opendotaclient.ui.model.PeerModel;
import com.example.opendotaclient.R;

import java.util.ArrayList;

public class PeerAdapter extends RecyclerView.Adapter<PeerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PeerModel> peerModelArrayList;

    public PeerAdapter(Context context, ArrayList<PeerModel> peerModelArrayList) {
        this.context = context;
        this.peerModelArrayList = peerModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.peer_item, parent, false);
        return new PeerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PeerModel peerModel = peerModelArrayList.get(position);
        holder.home_peer_name.setText(peerModel.getPeer_name());
        holder.home_peer_games.setText(peerModel.getPeer_games().toString());
        holder.home_peer_win_rate.setText(peerModel.getPeer_win_rate());
        holder.home_peer_last_played.setText(peerModel.getLast_played());
    }

    @Override
    public int getItemCount() {
        if (peerModelArrayList != null) {
            return peerModelArrayList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView home_peer_name, home_peer_games, home_peer_win_rate, home_peer_last_played;
        LinearLayout peer_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_peer_name = itemView.findViewById(R.id.home_peer_name);
            home_peer_games = itemView.findViewById(R.id.home_peer_games);
            home_peer_win_rate = itemView.findViewById(R.id.home_peer_win_rate);
            home_peer_last_played = itemView.findViewById(R.id.home_peer_last_played);
            peer_item = itemView.findViewById(R.id.peer_item);
        }
    }
}
