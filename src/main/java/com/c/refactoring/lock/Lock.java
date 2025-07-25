package com.c.refactoring.lock;

public class Lock {

    String lockReason;
    boolean readAccess;

    public Lock(boolean readAccess, String lockReason) {
        this.readAccess = readAccess;
        this.lockReason = lockReason;
    }

    public String getLockReason() {
        return lockReason;
    }

    public boolean isReadAccess() {
        return readAccess;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public void setRead(boolean readAccess) {
        this.readAccess = readAccess;
    }

}
