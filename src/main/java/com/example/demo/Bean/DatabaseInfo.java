package com.example.demo.Bean;

import org.springframework.beans.factory.annotation.Value;

public class DatabaseInfo {

    @Value("url")
    private String url;

    @Value("user")
    private String user;

    @Value("password")
    private String password;

    @Value("uniqueName")
    private String uniqueName;


    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    @SuppressWarnings("unused")
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    @SuppressWarnings("unused")
    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }
}
