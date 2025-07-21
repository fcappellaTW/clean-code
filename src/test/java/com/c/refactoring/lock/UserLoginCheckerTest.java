package com.c.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class UserLoginCheckerTest {
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    private static final String TEST_USER_ID = "TEST_USER_ID";
    private static final String TEST_USER_ID_1 = "TEST_USER_ID_1";
    private static final String TEST_USER_ID_2 = "TEST_USER_ID_2";

    @Test
    public void userAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] access = new Object[] { TEST_USER_ID_1, new Date() };

        Lock lock = createLock(access, TEST_USER_ID_2, true);

        assertLock(lock, true, Constants.LOCK_TEXT.replaceAll("@@USER@@", TEST_USER_ID_1));
    }

    @Test
    public void userAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] access = new Object[] { TEST_USER_ID, new Date() };

        Lock lock = createLock(access, TEST_USER_ID, true);

        assertLock(lock, false, null);
    }

    @Test
    public void userAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] access = new Object[] { TEST_USER_ID, new Date() };

        Lock lock = createLock(access, TEST_USER_ID, false);

        assertLock(lock, false, null);
    }

    @Test
    public void userAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] access = new Object[] { TEST_USER_ID_1, threeHoursBefore() };

        Lock lock = createLock(access, TEST_USER_ID_2, true);

        assertLock(lock, false, null);
    }

    @Test
    public void userAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] access = new Object[] { TEST_USER_ID_1, threeHoursBefore() };

        Lock lock = createLock(access, TEST_USER_ID_2, false);

        assertLock(lock, true, Constants.LOCK_TEXT.replaceAll("@@USER@@", TEST_USER_ID_1));
    }

    public Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

    private Lock createLock(Object[] access, String userId, boolean isFirstScreen) {
        return userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", isFirstScreen,
            new User(userId), Arrays.asList(new Object[] { access }));
    }

    private void assertLock(Lock lock, boolean isReadAccess, String lockReason) {
        assertEquals(isReadAccess, lock.isReadAccess());
        assertEquals(lockReason, lock.getLockReason());
    }

}
