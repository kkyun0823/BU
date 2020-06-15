package com.example.bu;

import java.io.Serializable;

public class Contents implements Serializable {
    private static final long serialVersionUID = 1L;
    private String request_id;
    private String dst_id;
    private String title;
    private String category;
    private String maintext;
    private String reply;
    private String dst_name;


    public Contents(){}
    public Contents(String request_id, String dst_id, String title, String category, String maintext, String dst_name){
        this.request_id = request_id;
        this.dst_id = dst_id;
        this.title = title;
        this.category = category;
        this.maintext = maintext;
        this.dst_name = dst_name;
    }
    public Contents(String request_id, String dst_id, String title, String category, String maintext, String reply, String dst_name){
        this(request_id,dst_id,title,category,maintext,dst_name);
        this.reply = reply;
    }

    public String getRequest_id(){
        return request_id;
    }

    public void setDst_name(String dst_name) {
        this.dst_name = dst_name;
    }

    public String getDst_name() {
        return dst_name;
    }

    public String getDst_id() {
        return dst_id;
    }

    public String getTitle(){
        return title;
    }
    public String getCategory(){
        return category;
    }
    public String getMaintext(){
        return maintext;
    }
    public String getReply(){
        return reply;
    }
    public void setReply(String reply){
        this.reply = reply;
    }

    public void setRequest_id(String reauest_id) {
        this.request_id = reauest_id;
    }

    public void setDst_id(String dst_id) {
        this.dst_id = dst_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }
}
