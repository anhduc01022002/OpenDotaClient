package com.example.opendotaclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.model.HeroModel;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HeroModel> heroModelArrayList;

    public HeroAdapter(Context context,ArrayList<HeroModel> heroModelArrayList){
        this.context = context;
        this.heroModelArrayList = heroModelArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false);
        return new HeroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HeroModel heroModel = heroModelArrayList.get(position);
//        holder.home_heroes_icon.setImageResource(heroModel.getHero_id());
        holder.home_heroes_name.setText(heroModel.getHero_id().toString());
        holder.home_heroes_games.setText(heroModel.getGames().toString());
        holder.home_heroes_win_rate.setText(heroModel.getWin_rate().toString());
        holder.home_heroes_last_played.setText(heroModel.getLast_played());
    }

    @Override
    public int getItemCount() {
        if (heroModelArrayList != null) {
            return heroModelArrayList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView home_heroes_icon;
        TextView home_heroes_name, home_heroes_games, home_heroes_win_rate, home_heroes_last_played;
        LinearLayout hero_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_heroes_icon = itemView.findViewById(R.id.home_heroes_icon);
            home_heroes_name = itemView.findViewById(R.id.home_heroes_name);
            home_heroes_games = itemView.findViewById(R.id.home_heroes_games);
            home_heroes_win_rate = itemView.findViewById(R.id.home_heroes_win_rate);
            home_heroes_last_played = itemView.findViewById(R.id.home_heroes_last_played);
            hero_item = itemView.findViewById(R.id.hero_item);
        }
    }
}
