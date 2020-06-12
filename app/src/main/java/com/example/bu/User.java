package com.example.bu;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String password;
    private String phoneNum;
    private String name;
    private String birth;
    private int state;
    private String major;

    public User(){}
    public User(String id, String password, String phoneNum, String name, String birth){
        this.id = id;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birth = birth;
    }
    public User(String id, String password, String phoneNum, String name, String birth, int state){
        this(id,password,phoneNum,name,birth);
        this.state=state;
    }
    public User(String id, String password, String phoneNum, String name, String birth, int state, String major){
        this(id,password,phoneNum,name,birth,state);
        this.major = major;
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

    public int getState(){return state;}

    public String getMajor(){return major;}

    public void setState(int state){this.state =  state;}

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

    public void setMajor(String major){this.major = major;}
}
