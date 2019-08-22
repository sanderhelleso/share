package com.semanta.share.utils;

import java.util.Timer;
import java.util.TimerTask;

import com.semanta.share.repository.ShareInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Deletes a directory & db rec after a given timeout
 */

public class DelDirTask extends TimerTask {
    private long MAX_TIMEOUT = 3600000 * 3; // 3 hrs
    private Timer timer;
    private File dir;

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    public DelDirTask(long timeout, String dirPath) {
        this.dir = new File(dirPath);
        this.timer = new Timer();

        if (timeout > MAX_TIMEOUT) {
            timeout = MAX_TIMEOUT;
        }

        this.timer.schedule(this, timeout);
    }

    @Override
    public void run() {
        String dirID = this.dir.getName();

        new File(FileSystem.concatZip(dirID)).delete();
        shareInfoRepo.deleteById(dirID);

        this.walkAndDelete(this.dir);
        this.timer.cancel();
    }

    private void walkAndDelete(File file) {
        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                walkAndDelete(f);
            }
        }

        file.delete();
    }
}