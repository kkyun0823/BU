package com.example.bu;

public class Contents {
    private String reauest_id;
    private String title;
    private String category;
    private String maintext;
    private String reply;


    public Contents(){}
    public Contents(String reauest_id, String title, String category, String maintext){
        this.reauest_id = reauest_id;
        this.title = title;
        this.category = category;
        this.maintext = maintext;

    }

    public String getReauest_id(){
        return reauest_id;
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

    public void setReauest_id(String reauest_id) {
        this.reauest_id = reauest_id;
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
