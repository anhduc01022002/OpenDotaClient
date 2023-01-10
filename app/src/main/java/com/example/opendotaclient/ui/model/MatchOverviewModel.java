package com.example.opendotaclient.ui.model;

public class MatchOverviewModel {
    private Boolean radiant_win;
    private String match_id;
    private Integer region;
    private Integer radiant_score;
    private Integer dire_score;
    private Integer game_mode;
    private String duration;
    private String radiant_team_name;
    private String dire_team_name;

    public Boolean getRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(Boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getRadiant_score() {
        return radiant_score;
    }

    public void setRadiant_score(Integer radiant_score) {
        this.radiant_score = radiant_score;
    }

    public Integer getDire_score() {
        return dire_score;
    }

    public void setDire_score(Integer dire_score) {
        this.dire_score = dire_score;
    }

    public Integer getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(Integer game_mode) {
        this.game_mode = game_mode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRadiant_team_name() {
        return radiant_team_name;
    }

    public void setRadiant_team_name(String radiant_team_name) {
        this.radiant_team_name = radiant_team_name;
    }

    public String getDire_team_name() {
        return dire_team_name;
    }

    public void setDire_team_name(String dire_team_name) {
        this.dire_team_name = dire_team_name;
    }
}

