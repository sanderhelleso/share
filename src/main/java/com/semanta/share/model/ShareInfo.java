package com.semanta.share.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "share_info")
public class ShareInfo {

    @Id
    private String id;

    private int totDownloads;
    private Date lastDownloadedAt;
    private Date expiresAt;
    private Boolean delOnFirstView;
    private String sharedFromCountry;

    public ShareInfo() {
        // for repo
    };

    public ShareInfo(String id, Date expiresAt, Boolean delOnFirstView, String sharedFromCountry) {
        this.id = id;
        this.totDownloads = 0;
        this.lastDownloadedAt = null;
        this.expiresAt = expiresAt;
        this.delOnFirstView = delOnFirstView;
        this.sharedFromCountry = sharedFromCountry;
    };

    public void setTotDownloads() {
        this.totDownloads += 1;
    }

    public void setLastDownloadedAt() {
        this.lastDownloadedAt = new Date();
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