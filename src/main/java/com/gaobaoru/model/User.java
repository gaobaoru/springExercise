package com.gaobaoru.model;

/**
 * Created by gaobaoru on 2017/2/22.
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSomething(){
        return "My name is " + name;
    }
}
