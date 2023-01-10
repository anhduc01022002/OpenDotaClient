package com.example.opendotaclient.ui.model;

public class MatchModel {
    private Long match_id;
    private Integer hero;
    private String mode;
    private String kda;
    private String length;
    private String ended;

    public MatchModel(Long match_id, Integer hero, String mode, String kda, String length, String ended) {
        this.match_id = match_id;
        this.hero = hero;
        this.mode = mode;
        this.kda = kda;
        this.length = length;
        this.ended = ended;
    }

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Integer getHero() {
        return hero;
    }

    public void setHero(int hero) {
        this.hero = hero;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }
}
