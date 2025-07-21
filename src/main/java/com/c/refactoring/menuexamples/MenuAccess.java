package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles) {

        if (roles == null)
            return;

        menuItemsList.stream().forEach(menuItem -> setAccessMenuItem(menuItem, roles));
    }

    private void setAccessMenuItem(MenuItem menuItem, Role[] roles) {
        boolean hasWriteAccess = validateAccess(roles, menuItem.getWriteAccessRole());
        boolean hasReadAccess = validateAccess(roles, menuItem.getReadAccessRole());

        if (hasReadAccess)
            menuItem.setAccess(Constants.READ);

        if (hasWriteAccess)
            menuItem.setAccess(Constants.WRITE);

        menuItem.setVisible(hasReadAccess || hasWriteAccess);
    }

    private boolean validateAccess(Role[] roles, String roleName) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(roleName));
    }

}
