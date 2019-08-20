package com.semanta.share.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShareInfo {

    @Id
    @GeneratedValue
    private int id;

    private int totDownloads;
    private Date lastDownloadedAt;
    private Date expiresAt;
    private Boolean delOnEnter;
    private String sharedFromCountry;
    private HashMap<String, Integer> countryDownloads = new HashMap<String, Integer>();

    public ShareInfo(int totDownloads, Date lastDownloadedAt, Date expiresAt, Boolean delOnEnter,
            String sharedFromCountry, HashMap<String, Integer> countryDownloads) {
        this.totDownloads = totDownloads;
        this.lastDownloadedAt = lastDownloadedAt;
        this.expiresAt = expiresAt;
        this.delOnEnter = delOnEnter;
        this.sharedFromCountry = sharedFromCountry;
        this.countryDownloads = countryDownloads;
    }

    public int getTotalDownloads() {
        return this.totDownloads;
    }

    public Date getLastDownloadedAt() {
        return this.lastDownloadedAt;
    }

    public Date getExpiresAt() {
        return this.expiresAt;
    }

    public Boolean getDelOnEnter() {
        return this.delOnEnter;
    }

    public String getSharedFromCountry() {
        return this.sharedFromCountry;
    }

    public HashMap<String, Integer> getCountryDownloads() {
        return this.countryDownloads;
    }
}