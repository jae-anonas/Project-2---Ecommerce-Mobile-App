package com.example.roosevelt.project_2___ecommerce_mobile_app;

/**
 * Created by roosevelt on 7/23/16.
 */
public class User {
    private long id;
    private String name, password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(long id, String name, String password) {

        this.id = id;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
