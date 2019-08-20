package com.semanta.share.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShareAccessedFrom {

    @Id
    @GeneratedValue
    private String id;

    private String country;
    private int count;
    private String shareID;

    public ShareAccessedFrom(String country, int count, String shareID) {
        this.country = country;
        this.count = count;
        this.shareID = shareID;
    }

    public void incrementCount() {
        this.count += 1;
    }

    public String incrementCountry() {
        return this.country;
    }

    public String getShareID() {
        return this.shareID;
    }

    public String getCountry() {
        return this.country;
    }

    public int getCount() {
        return this.count;
    }
}