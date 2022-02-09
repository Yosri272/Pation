package com.example.dell.pation;

/**
 * Created by DELL on 2/18/2018.
 */


public class doctor {
    String name,spe,d_mo,d_address,d_balans;
    //int d_balans;
    public doctor(String name,String spe,String d_mo,String d_address,String d_balans){
        this.name=name;
        this.spe=spe;
        this.d_balans=d_balans;
        this.d_address=d_address;
        this.d_mo=d_mo;


    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }




    public String getd_balans() {
        return d_balans;
    }

    public void setd_balans(String d_balans) {
        this.d_balans = d_balans;
    }

    public String getd_address() {
        return d_address;
    }

    public void setd_address(String d_address) {
        this.d_address = d_address;
    }

    public String getd_mo() {
        return d_mo;
    }

    public void setd_mo(String d_mo) {
        this.d_mo = d_mo;
    }



}

