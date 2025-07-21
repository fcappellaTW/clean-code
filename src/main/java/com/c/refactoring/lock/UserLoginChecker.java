package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    private static final int MAX_LOCK_TIME_MS = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status,
            boolean isFirstScreen, User userLoggingIn, List<Object> existingLocks) {

        Object[] existingLock = (Object[]) existingLocks.get(0);
        String userLockId = (String) existingLock[0];

        if (existingLocks.isEmpty() || existingLock[0] == null)
            return createNewLock(false, null);

        if (userLockId == null || userLockId.equalsIgnoreCase(userLoggingIn.getUserId()))
            return createNewLock(false, null);

        Date lockTimestamp = (Date) existingLock[1];
        Boolean passedMaxLockTime = new Date().getTime() - lockTimestamp.getTime() > MAX_LOCK_TIME_MS;

        if (passedMaxLockTime && isFirstScreen)
            return createNewLock(false, null);

        return createNewLock(true, Constants.LOCK_TEXT.replaceAll("@@USER@@", userLockId));
    }

    private Lock createNewLock(Boolean isRead, String lockMsg) {
        return new Lock(isRead, lockMsg);
    }
}