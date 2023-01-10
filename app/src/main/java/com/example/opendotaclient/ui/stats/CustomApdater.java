package com.example.opendotaclient.ui.stats;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opendotaclient.R;

import java.util.List;

public class CustomApdater extends BaseAdapter {

    private LayoutInflater flater;
    private List<RankObject> rankObjectList;
    private int listItemLayoutResource;
    private int textViewItemIcon;
    private int textViewItemName;

    public CustomApdater(Activity activity, int listItemLayoutResource, int textViewItemIcon, int textViewItemName, List<RankObject> rankObjectList){
        this.listItemLayoutResource = listItemLayoutResource;
        this.textViewItemIcon = textViewItemIcon;
        this.textViewItemName = textViewItemName;
        this.rankObjectList = rankObjectList;
        this.flater = activity.getLayoutInflater();

    }

    @Override
    public int getCount() {
        if (rankObjectList == null) {
            return 0;
        } else {
            return rankObjectList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return rankObjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        RankObject rankObject = (RankObject) this.getItem(position);
        return rankObject.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RankObject rankObject = (RankObject) getItem(position);

        View rollView = this.flater.inflate(this.listItemLayoutResource, null, true);
        //set name
        TextView textViewName = rollView.findViewById(this.textViewItemName);
        textViewName.setText(rankObject.getRank_name());
        // set icon
        ImageView iconRank = rollView.findViewById(this.textViewItemIcon);
        // thay icon theo rank
        if ("Divine".equals(rankObject.getRank_name())){
            iconRank.setImageResource(R.drawable.dire_logo);
        } else {
            iconRank.setImageResource(R.drawable.dire_logo);
        }
//        String s = "R.drawable." + rankObject.getRank_icon();
//        iconRank.setImageResource(Integer.parseInt(s));

        return rollView;
    }
}
