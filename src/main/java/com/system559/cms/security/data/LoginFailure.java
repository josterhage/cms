package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

public class LoginFailure {
    @Id
    private String userId;
    private long lastOccurrence;
    private long count;

    //TODO: make FAILURE_TIMEOUT configurable
    private static final long FAILURE_TIMEOUT = 15 * 60 * 1000;
    private static final int MAX_FAILURES = 3;

    public LoginFailure() {
    }

    public LoginFailure(String userId) {
        this.userId = userId;
    }


    public LoginFailure(String userID, long lastOccurrence, long count) {
        this.userId = userID;
        this.lastOccurrence = lastOccurrence;
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public long getLastOccurrence() {
        return lastOccurrence;
    }

    public long getCount() {
        return count;
    }

    public void registerFailure() {
        if(System.currentTimeMillis() - lastOccurrence > FAILURE_TIMEOUT) {
            count = 0;
        }
        lastOccurrence = System.currentTimeMillis();
        count++;
    }

    public static long getFailureTimeout() {
        return FAILURE_TIMEOUT;
    }

    public static int getMaxFailures() {
        return MAX_FAILURES;
    }
}
