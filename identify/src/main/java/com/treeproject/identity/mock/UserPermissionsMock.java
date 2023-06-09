package com.treeproject.identity.mock;

import java.util.HashMap;
import java.util.Map;

public class UserPermissionsMock {
    private UserPermissionsMock() {

    }

    public static Map<String, Object> get(String role) {
        HashMap<String, Object> permissions = new HashMap<>();
        permissions.put("show_accounts", true);
        if (role.equals("ADMIN")) {
            permissions.put("date_range", true);
            permissions.put("amount_range", true);
        } else if (role.equals("USER")) {
            permissions.put("date_range", false);
            permissions.put("amount_range", false);
        }
        return permissions;
    }

}
