package com.happyge.empl.model;

import java.util.Date;

public class HappygeAccountRole {
    private Long id;

    private Date timestamp;

    private String username;

    private String label;

  public HappygeAccountRole() {
  }

  public HappygeAccountRole(String username, String rolelabel) {
    this.username = username;
    this.label = rolelabel;
  }

  private HappygeAccountRole(long id, Date timestamp, String username,
                             String label) {
    super();
    this.id = id;
    this.timestamp = timestamp;
    this.username = username;
    this.label = label;
  }


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}