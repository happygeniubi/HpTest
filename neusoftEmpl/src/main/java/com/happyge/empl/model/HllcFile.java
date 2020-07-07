package com.happyge.empl.model;


public class HllcFile {

  private int flID;
  private String flTitle;
  private String flPeople;
  private String flDate;
  private String flDescribe;
  private String flFile;


    @Override
    public String toString() {
        return "HllcFile{" +
                "flID=" + flID +
                ", flTitle='" + flTitle + '\'' +
                ", flPeople='" + flPeople + '\'' +
                ", flDate='" + flDate + '\'' +
                ", flDescribe='" + flDescribe + '\'' +
                ", flFile='" + flFile + '\'' +
                '}';
    }

    public int getFlID() {
    return flID;
  }

  public void setFlID(int flID) {
    this.flID = flID;
  }

  public String getFlTitle() {
    return flTitle;
  }

  public void setFlTitle(String flTitle) {
    this.flTitle = flTitle;
  }


  public String getFlPeople() {
    return flPeople;
  }

  public void setFlPeople(String flPeople) {
    this.flPeople = flPeople;
  }


  public String getFlDate() {
    return flDate;
  }

  public void setFlDate(String flDate) {
    this.flDate = flDate;
  }


  public String getFlDescribe() {
    return flDescribe;
  }

  public void setFlDescribe(String flDescribe) {
    this.flDescribe = flDescribe;
  }

    public String getFlFile() {
        return flFile;
    }

    public void setFlFile(String flFile) {
        this.flFile = flFile;
    }
}
