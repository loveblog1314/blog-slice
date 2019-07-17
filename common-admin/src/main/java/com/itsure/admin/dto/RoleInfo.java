package com.itsure.admin.dto;

import com.itsure.admin.entity.Permission;
import com.itsure.admin.entity.Role;

import java.io.Serializable;
import java.util.List;

public class RoleInfo extends Role implements Serializable {

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}