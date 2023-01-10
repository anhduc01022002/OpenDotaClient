package com.example.opendotaclient.ui.stats;

public class RankObject {
    int id;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    String rank_icon;
    String rank_name;

    public RankObject(int id, String rank_icon, String rank_name) {
        this.id = id;
        this.rank_icon = rank_icon;
        this.rank_name = rank_name;
    }

    public RankObject(String rank_icon, String rank_name) {
        this.rank_icon = rank_icon;
        this.rank_name = rank_name;
    }

    public String getRank_icon() {

        return rank_icon;
    }

    public void setRank_icon(String rank_icon) {

        this.rank_icon = rank_icon;
    }

    public String getRank_name() {

        return rank_name;
    }

    public void setRank_name(String rank_name) {

        this.rank_name = rank_name;
    }
}

