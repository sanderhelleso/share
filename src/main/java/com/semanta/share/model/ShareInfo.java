package com.semanta.share.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShareInfo {

    @Id
    @GeneratedValue
    private Long id;

    private int totDownloads;
    private Date lastDownloadedAt;
    private Date expiresAt;
    private Boolean delOnFirstView;
    private String sharedFromCountry;

    protected ShareInfo() {
        // for repo
    };

    public ShareInfo(int totDownloads, Date lastDownloadedAt, Date expiresAt, Boolean delOnFirstView,
            String sharedFromCountry) {
        this.totDownloads = totDownloads;
        this.lastDownloadedAt = lastDownloadedAt;
        this.expiresAt = expiresAt;
        this.delOnFirstView = delOnFirstView;
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

    public Boolean getdelOnFirstView() {
        return this.delOnFirstView;
    }

    public String getSharedFromCountry() {
        return this.sharedFromCountry;
    }
}