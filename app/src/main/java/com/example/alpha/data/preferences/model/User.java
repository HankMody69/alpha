package com.example.alpha.data.preferences.model;

public class User {

    private static User instance = null;

    private String id;
    private String phone;
    private String udid;
    private String token;

    private User() {}

    public static User getInstance() {
        if (instance == null) {
            return new User();
        } else {
            return instance;
        }
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getUdid() {
        return udid;
    }

    public String getToken() { return token; }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public void setToken(String token) { this.token = token; }
}
