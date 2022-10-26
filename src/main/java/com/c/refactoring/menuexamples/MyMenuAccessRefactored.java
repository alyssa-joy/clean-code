package com.c.refactoring.menuexamples;

import java.util.List;

public class MyMenuAccessRefactored {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles) {
        if (roles != null) {
            for (int i = 0; i < menuItemsList.size(); i++) {

                MenuItem menuItem = menuItemsList.get(i);

                for (int j = 0; j < roles.length; j++) {

                    String role = roles[j].getName();

                    if (hasWriteAccess(role, menuItem)) {
                        setAccessAndVisibility(menuItem, Constants.WRITE);
                    } else if (hasReadAccess(role, menuItem)) {
                        setAccessAndVisibility(menuItem, Constants.READ);
                    }
                }
            }
        }
    }

    private void setAccessAndVisibility(MenuItem menuItem, String access) {
        menuItem.setAccess(access);
        menuItem.setVisible(true);
    }

    private boolean hasWriteAccess(String role, MenuItem menuItem) {
        if (role.equals(menuItem.getWriteAccessRole())) {
            return true;
        }
        return false;
    }


    private boolean hasReadAccess(String role, MenuItem menuItem) {
        if (role.equals(menuItem.getReadAccessRole())) {
            return true;
        }
        return false;
    }
}
