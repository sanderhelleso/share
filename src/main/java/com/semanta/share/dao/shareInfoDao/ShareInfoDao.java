package com.semanta.share.dao.shareInfoDao;

import com.semanta.share.model.ShareInfo;

public interface ShareInfoDao {
    public Boolean insertShareInfo(ShareInfo shareInfo);

    public Boolean deleteShareInfo(int id);

    public ShareInfo findShareInfo();
}