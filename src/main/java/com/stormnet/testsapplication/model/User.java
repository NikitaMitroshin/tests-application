package com.stormnet.testsapplication.model;

public class User {

    private int id;
    private String name;
    private String lastName;
    private String login;
    private String password;
    private int roleId;

    public User(int id, String name, String lastName, String login, String password, int roleId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public User(int id, String name, String lastName, String login, int roleId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
