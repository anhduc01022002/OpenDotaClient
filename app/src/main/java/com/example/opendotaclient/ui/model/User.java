package com.example.opendotaclient.ui.model;

public class User {

    private int avatar;
    private String name;
    private Long id;


    public User(int avatar, String name, Long id) {
        this.avatar = avatar;
        this.name = name;
        this.id = id;
    }

    public int getAvatar() {

        return avatar;
    }

    public void setAvatar(int avatar) {

        this.avatar = avatar;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }
}
