package com.happyge.empl.model;

import java.util.Date;

public class HappygeModule {
    private Long id;

    private Date timestamp;

    private String name;

    private String code;

    private String supercode;

    private String page;

    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSupercode() {
        return supercode;
    }

    public void setSupercode(String supercode) {
        this.supercode = supercode == null ? null : supercode.trim();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}