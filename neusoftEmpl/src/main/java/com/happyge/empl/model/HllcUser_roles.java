package com.happyge.empl.model;

public class HllcUser_roles {
    private String user_id;
    private int role_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "HllcUser_roles{" +
                "user_id='" + user_id + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
