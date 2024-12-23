package com.example.souklalla;

public class women_helperclass {
    String women_name, women_lastn, women_phone, women_email, women_wilaya, women_pass, womenId;

    // Constructor
    public women_helperclass(String women_name, String women_pass, String women_wilaya, String women_email, String women_phone, String women_lastn, String womenId) {
        this.women_name = women_name;
        this.women_pass = women_pass;
        this.women_wilaya = women_wilaya;
        this.women_email = women_email;
        this.women_phone = women_phone;
        this.women_lastn = women_lastn;
        this.womenId = womenId;  // Set the womenId
    }

    // Getters and Setters for all fields, including the womenId

    public String getWomen_name() {
        return women_name;
    }

    public void setWomen_name(String women_name) {
        this.women_name = women_name;
    }

    public String getWomen_wilaya() {
        return women_wilaya;
    }

    public void setWomen_wilaya(String women_wilaya) {
        this.women_wilaya = women_wilaya;
    }

    public String getWomen_pass() {
        return women_pass;
    }

    public void setWomen_pass(String women_pass) {
        this.women_pass = women_pass;
    }

    public String getWomen_email() {
        return women_email;
    }

    public void setWomen_email(String women_email) {
        this.women_email = women_email;
    }

    public String getWomen_phone() {
        return women_phone;
    }

    public void setWomen_phone(String women_phone) {
        this.women_phone = women_phone;
    }

    public String getWomen_lastn() {
        return women_lastn;
    }

    public void setWomen_lastn(String women_lastn) {
        this.women_lastn = women_lastn;
    }

    public String getWomenId() {
        return womenId;  // Getter for womenId
    }

    public void setWomenId(String womenId) {
        this.womenId = womenId;  // Setter for womenId
    }
}
