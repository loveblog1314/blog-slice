package com.itsure.admin.dto;

import java.io.Serializable;


public class FrontMenuInfo implements Serializable {

    private Integer id;         // 菜单id

    private String name;        // 菜单名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
