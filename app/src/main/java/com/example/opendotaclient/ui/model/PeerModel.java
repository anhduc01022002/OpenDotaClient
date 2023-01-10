package com.example.opendotaclient.ui.model;

public class PeerModel {
    private String peer_name;
    private Integer peer_games;
    private String peer_win_rate;
    private String last_played;

    public PeerModel(String peer_name, Integer peer_games, String peer_win_rate, String last_played) {
        this.peer_name = peer_name;
        this.peer_games = peer_games;
        this.peer_win_rate = peer_win_rate;
        this.last_played = last_played;
    }

    public String getPeer_name() {
        return peer_name;
    }

    public void setPeer_name(String peer_name) {
        this.peer_name = peer_name;
    }

    public Integer getPeer_games() {
        return peer_games;
    }

    public void setPeer_games(Integer peer_games) {
        this.peer_games = peer_games;
    }

    public String getPeer_win_rate() {
        return peer_win_rate;
    }

    public void setPeer_win_rate(String peer_win_rate) {
        this.peer_win_rate = peer_win_rate;
    }

    public String getLast_played() {
        return last_played;
    }

    public void setLast_played(String last_played) {
        this.last_played = last_played;
    }
}
