package com.example.souklalla;

public class user_helperclass {

    String user_name,user_lastn,user_phone,user_email,user_wilaya,user_pass,userId;
    String user_img;
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_lastn() {
        return user_lastn;
    }

    public void setUser_lastn(String user_lastn) {
        this.user_lastn = user_lastn;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_wilaya() {
        return user_wilaya;
    }

    public void setUser_wilaya(String user_wilaya) {
        this.user_wilaya = user_wilaya;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUser_img() {
        return user_img;
    }
    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public user_helperclass(String user_name, String user_phone, String user_lastn, String user_email, String user_wilaya, String user_pass, String userId, String user_img) {
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_lastn = user_lastn;
        this.user_email = user_email;
        this.user_wilaya = user_wilaya;
        this.user_pass = user_pass;
        this.userId = userId;
        this.user_img = user_img;

    }
}
