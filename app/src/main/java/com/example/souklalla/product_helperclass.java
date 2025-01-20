package com.example.souklalla;

import android.graphics.Bitmap;
import android.net.Uri;

public class product_helperclass {
    String prod_name,prod_price,prod_desc,prod_wname,prod_type,prodId;
    String prod_img;

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_img() {
        return prod_img;
    }

    public void setProd_img(String prod_img) {
        this.prod_img = prod_img;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public String getProd_wname() {
        return prod_wname;
    }

    public void setProd_wname(String prod_wname) {
        this.prod_wname = prod_wname;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public product_helperclass(String prod_name, String prod_img, String prod_price, String prod_desc, String prod_wname, String prod_type, String prodId) {
        this.prod_name = prod_name;
        this.prod_img = prod_img;
        this.prod_price = prod_price;
        this.prod_desc = prod_desc;
        this.prod_wname = prod_wname;
        this.prod_type = prod_type;
        this.prodId = prodId;
    }
    public product_helperclass() {
    }

}
