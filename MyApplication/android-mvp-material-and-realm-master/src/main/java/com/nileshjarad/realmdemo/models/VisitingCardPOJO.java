package com.nileshjarad.realmdemo.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class VisitingCardPOJO extends RealmObject {

   @PrimaryKey  private int no;
    private String name;
    private String address;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}



