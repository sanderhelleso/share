package com.semanta.share.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

/**
 * Deletes a directory after a given timeout
 */

public class DelDirTask extends TimerTask {
    private Timer timer;
    private File dir;

    public DelDirTask(int timeout, String dirPath) {
        this.dir = new File(dirPath);
        this.timer = new Timer();
        this.timer.schedule(this, timeout);
    }

    @Override
    public void run() {
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