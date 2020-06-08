package com.example.bu;

public class User {
    private String id;
    private String password;
    private String phoneNum;
    private String name;
    private String birth;

    public User(){}
    public User(String id, String password, String phoneNum, String name, String birth){
        this.id = id;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public String getBirth() {
        return birth;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
