package com.example.opendotaclient.ui.model;

public class HeroModel {
    private String hero_id;
    private String last_played;
    private Integer games;
    private String win_rate;

    public HeroModel(String hero_id, String last_played, Integer games, String win_rate) {
        this.hero_id = hero_id;
        this.last_played = last_played;
        this.games = games;
        this.win_rate = win_rate;
    }

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public String getLast_played() {
        return last_played;
    }

    public void setLast_played(String last_played) {
        this.last_played = last_played;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public String getWin_rate() {
        return win_rate;
    }

    public void setWin_rate(String win_rate) {
        this.win_rate = win_rate;
    }
}
