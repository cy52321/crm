package com.cheny.springboot.domain;


public class User {
    private String id;

    private String name;

    private String loginAct;

    private String loginPwd;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginact() {
        return loginAct;
    }

    public void setLoginact(String loginact) {
        this.loginAct = loginact;
    }

    public String getLoginpwd() {
        return loginPwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginPwd = loginpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}