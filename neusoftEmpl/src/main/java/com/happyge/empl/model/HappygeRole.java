package com.happyge.empl.model;

import java.util.Date;

public class HappygeRole {
    private Long id;

    private Date timestamp;

    private String rolename;

    private String roledescription;

    private String rolelabel;

    private Date creation;

    private String creator;

    private HappygeRole happygeRole;

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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledescription() {
        return roledescription;
    }

    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription == null ? null : roledescription.trim();
    }

    public String getRolelabel() {
        return rolelabel;
    }

    public void setRolelabel(String rolelabel) {
        this.rolelabel = rolelabel == null ? null : rolelabel.trim();
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}