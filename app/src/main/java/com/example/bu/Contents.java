package com.example.bu;

public class Contents {
    private String request_id;
    private String dst_id;
    private String title;
    private String category;
    private String maintext;
    private String reply;


    public Contents(){}
    public Contents(String reauest_id, String dst_id, String title, String category, String maintext){
        this.request_id = reauest_id;
        this.dst_id = dst_id;
        this.title = title;
        this.category = category;
        this.maintext = maintext;

    }

    public String getRequest_id(){
        return request_id;
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
