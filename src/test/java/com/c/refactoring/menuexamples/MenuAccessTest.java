package com.c.refactoring.menuexamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MenuAccessTest {

    private static final Role MENU_READ_ROLE = new Role("MenuRead");
    private static final Role MENU_WRITE_ROLE = new Role("MenuWrite");
    private static final MenuItem MENU = new MenuItem("A", "MenuRead", "MenuWrite");

    MenuAccess menuAccess = new MenuAccess();

    @Test
    public void testSetAuthorizations_UserHasNoRoles() {
        Role[] userRoles = {};

        System.out.println("testSetAuthorizations_UserHasNoRoles");

        List<MenuItem> menuItems = Arrays.asList(MENU);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItem(menuItems.get(0), null, false);
    }

    @Test
    public void testSetAuthorizations_UserHasOnlyReadRole() {
        Role[] userRoles = { MENU_READ_ROLE };

        System.out.println("testSetAuthorizations_UserHasOnlyReadRole");

        List<MenuItem> menuItems = Arrays.asList(MENU);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItem(menuItems.get(0), Constants.READ, true);
    }

    @Test
    public void testSetAuthorizations_UserHasReadAndWriteRoles() {
        Role[] userRoles = { MENU_READ_ROLE, MENU_WRITE_ROLE };

        System.out.println("testSetAuthorizations_UserHasReadAndWriteRoles");

        List<MenuItem> menuItems = Arrays.asList(MENU);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItem(menuItems.get(0), Constants.WRITE, true);
    }

    @Test
    public void testSetAuthorizations_UserHasOnlyWriteRole() {
        Role[] userRoles = { MENU_WRITE_ROLE };

        System.out.println("testSetAuthorizations_UserHasOnlyWriteRole");

        List<MenuItem> menuItems = Arrays.asList(MENU);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItem(menuItems.get(0), Constants.WRITE, true);
    }

    private void assertMenuItem(MenuItem item, String access, Boolean visible) {
        assertEquals(access, item.getAccess());
        assertEquals(visible, item.isVisible());
    }
}
