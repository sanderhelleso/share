package com.semanta.share.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShareInfo {

    @Id
    private String id;

    private int totDownloads;
    private Date lastDownloadedAt;
    private Date expiresAt;
    private String sharedFromCountry;
    private HashMap<String, Integer> shareAccessedFrom;

    public ShareInfo() {
        // for repo
    };

    public ShareInfo(String id, Date expiresAt, String sharedFromCountry) {
        this.id = id;
        this.totDownloads = 0;
        this.lastDownloadedAt = null;
        this.expiresAt = expiresAt;
        this.sharedFromCountry = sharedFromCountry;
        this.shareAccessedFrom = new HashMap<String, Integer>();
    };

    public void setTotDownloads() {
        this.totDownloads += 1;
    }

    public void setLastDownloadedAt() {
        this.lastDownloadedAt = new Date();
    }

    public void addShareAccessedFrom(String country) {
        int count = 0;
        if (this.shareAccessedFrom.containsKey(country)) {
            count = this.shareAccessedFrom.get(country);
        }

        this.shareAccessedFrom.put(country, count + 1);
    }

    public String getId() {
        return this.id;
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

    public String getSharedFromCountry() {
        return this.sharedFromCountry;
    }

    public HashMap<String, Integer> getShareAccessedFrom() {
        return this.shareAccessedFrom;
    }
}