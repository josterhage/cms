package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

public class AccountLock {
    @Id
    private String userId;
    private long endTime;

    //TODO: make LOCK_TIMEOUT configurable
    private final static long LOCK_TIMEOUT = 60 * 60 * 1000;

    public AccountLock() {
    }

    public AccountLock(String userID) {
        this.userId = userID;
        endTime = System.currentTimeMillis() + LOCK_TIMEOUT;
    }

    public AccountLock(String userID, long endTime) {
        this.userId = userID;
        this.endTime = endTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isValid() {
        return System.currentTimeMillis() < endTime;
    }

    public static long getLockTimeOut() {
        return LOCK_TIMEOUT;
    }
}
