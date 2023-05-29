package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

/**
 * Record of an active lock on a specified user account.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class AccountLock {
    /**
     * Unique identifier of the specified user.
     */
    @Id
    private String userId;
    /**
     * Time the lock ends in milliseconds since the Unix Epoch
     */
    private long endTimeInMillisecondsSinceEpoch;

    /**
     * Default lock timeout in milliseconds.
     */
    //TODO: make LOCK_TIMEOUT configurable
    private final static long LOCK_TIMEOUT_IN_MS = 60 * 60 * 1000;

    /**
     * Returns an empty AccountLock instance
     */
    public AccountLock() {
    }

    /**
     * Returns a new AccountLock for the presented userId that ends {@link #LOCK_TIMEOUT_IN_MS} from instantiation.
     * @param userID unique identifier of the locked user account
     */
    public AccountLock(String userID) {
        this.userId = userID;
        endTimeInMillisecondsSinceEpoch = System.currentTimeMillis() + LOCK_TIMEOUT_IN_MS;
    }

    /**
     * Returns a new AccountLock for the given userId that ends after a specified time.
     * @param userID unique identifier of the locked user account
     * @param endTime end time in milliseconds after the Unix Epoch
     */
    public AccountLock(String userID, long endTime) {
        this.userId = userID;
        this.endTimeInMillisecondsSinceEpoch = endTime;
    }

    /**
     * Returns the lock's end time in milliseconds after the Unix Epoch
     * @return end time in milliseconds after Unix Epoch
     */
    public long getEndTimeInMillisecondsSinceEpoch() {
        return endTimeInMillisecondsSinceEpoch;
    }

    /**
     * Returns true if the lock's end time has passed.
     * @return <b>true</b> if the end time has passed, <b>false</b> otherwise.
     */
    public boolean isValid() {
        return System.currentTimeMillis() < endTimeInMillisecondsSinceEpoch;
    }

    /**
     * Returns the default lock timeout in milliseconds.
     * @return default lock timeout in milliseconds.
     */
    public static long getLockTimeOut() {
        return LOCK_TIMEOUT_IN_MS;
    }
}
