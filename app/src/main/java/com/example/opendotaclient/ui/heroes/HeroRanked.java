package com.example.opendotaclient.ui.heroes;

public class HeroRanked {
    String hero_name;
    String rank;
    String ban;
    String win_rate;


    public HeroRanked(String rank, String hero_name, String win_rate, String ban) {
        this.hero_name = hero_name;
        this.rank = rank;
        this.ban = ban;
        this.win_rate = win_rate;
    }

    public HeroRanked(String hero_name, String win_rate, String ban) {
        this.hero_name = hero_name;
        this.ban = ban;
        this.win_rate = win_rate;
    }

    public String getHero_name() {
        return hero_name;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getWin_rate() {
        return win_rate;
    }

    public void setWin_rate(String win_rate) {
        this.win_rate = win_rate;
    }
}
