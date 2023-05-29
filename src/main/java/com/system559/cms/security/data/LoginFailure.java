package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

/**
 * Record of login failures on a given user account.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginFailure {
    /**
     * Unique identifier of the user account.
     */
    @Id
    private String userId;
    /**
     * Time of last occurrence in milliseconds since the Unix Epoch.
     */
    private long lastOccurrenceInMillisecondsSinceEpoch;
    /**
     * Count of failures since this record was created or reset.
     */
    private long failureCount;

    /**
     * Default timeout for failures to accumulate.
     */
    //TODO: make FAILURE_TIMEOUT configurable
    private static final long FAILURE_TIMEOUT = 15 * 60 * 1000;
    /**
     * Default maximum failures.
     */
    private static final int MAX_FAILURES = 3;

    /**
     * Returns an empty LoginFailure instance.
     */
    public LoginFailure() {
    }

    /**
     * Returns a LoginFailure record for the specified user.
     * @param userId unique identifier of the specified user.
     */
    public LoginFailure(String userId) {
        this.userId = userId;
    }

    /**
     * Returns a fully populated LoginFailure record for the specified user.
     * @param userID unique identifier of the specified user.
     * @param lastOccurrence time of last failure in milliseconds since the unix epoch.
     * @param failureCount count of failures.
     */
    public LoginFailure(String userID, long lastOccurrence, long failureCount) {
        this.userId = userID;
        this.lastOccurrenceInMillisecondsSinceEpoch = lastOccurrence;
        this.failureCount = failureCount;
    }

    /**
     * Returns the unique identifier of the user associated with this record.
     * @return unique user identifier.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the time of the last failure in milliseconds since the Unix Epoch.
     * @return time of the last failure.
     */
    public long getLastOccurrenceInMillisecondsSinceEpoch() {
        return lastOccurrenceInMillisecondsSinceEpoch;
    }

    /**
     * Count of failures
     * @return count of failures
     */
    public long getFailureCount() {
        return failureCount;
    }

    /**
     * Registers a login failure. If the last failure was longer than FAILURE_TIMEOUT milliseconds ago,
     * the failureCount is reset to 0. In all cases, the time of last occurrence is set to now and the failure count
     * is incremented.
     */
    public void registerFailure() {
        if(System.currentTimeMillis() - lastOccurrenceInMillisecondsSinceEpoch > FAILURE_TIMEOUT) {
            failureCount = 0;
        }
        lastOccurrenceInMillisecondsSinceEpoch = System.currentTimeMillis();
        failureCount++;
    }

    /**
     * Returns the failure timeout.
     * @return failure timeout.
     */
    public static long getFailureTimeout() {
        return FAILURE_TIMEOUT;
    }

    /**
     * Returns the maximum allowable failures before an account is locked.
     * @return maximum failures.
     */
    public static int getMaxFailures() {
        return MAX_FAILURES;
    }
}
