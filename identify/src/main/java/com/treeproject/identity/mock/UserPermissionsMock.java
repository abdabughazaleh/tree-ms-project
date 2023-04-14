package com.treeproject.identity.mock;

import java.util.HashMap;

public class UserPermissionsMock {
    public static HashMap<String, Object> get(String role) {
        HashMap<String, Object> permissions = new HashMap<>();
        permissions.put("show_accounts", true);
        if (role.equals("ADMIN")) {
            permissions.put("date_range", true);
            permissions.put("amount_range", true);
        }
        return permissions;
    }

}
