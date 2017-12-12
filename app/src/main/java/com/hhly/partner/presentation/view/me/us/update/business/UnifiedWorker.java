package com.hhly.partner.presentation.view.me.us.update.business;

/**
 * 版本更新具体的唯一执行类
 *  Created by dell on 2017/5/17.
 */
public class UnifiedWorker {

    private volatile boolean isRunning;

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isRunning () {
        return isRunning;
    }
}
