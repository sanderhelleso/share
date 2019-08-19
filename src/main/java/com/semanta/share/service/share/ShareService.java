package com.semanta.share.service.share;

import java.util.ArrayList;

public interface ShareService {
    public abstract String upload(String delOnFirstView, int timeout);

    public abstract ArrayList<String> retrieve(String dirNonce);
}