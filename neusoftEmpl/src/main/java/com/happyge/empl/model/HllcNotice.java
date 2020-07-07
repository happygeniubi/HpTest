package com.happyge.empl.model;


public class HllcNotice {

  private int noId;
  private String noTitle;
  private String noMessage;
  private String noDate;
  private String noPeople;

  @Override
  public String toString() {
    return "HllcNotice{" +
            "noId=" + noId +
            ", noTitle='" + noTitle + '\'' +
            ", noMessage='" + noMessage + '\'' +
            ", noDate='" + noDate + '\'' +
            ", noPeople='" + noPeople + '\'' +
            '}';
  }

  public int getNoId() {
    return noId;
  }

  public void setNoId(int noId) {
    this.noId = noId;
  }


  public String getNoTitle() {
    return noTitle;
  }

  public void setNoTitle(String noTitle) {
    this.noTitle = noTitle;
  }


  public String getNoMessage() {
    return noMessage;
  }

  public void setNoMessage(String noMessage) {
    this.noMessage = noMessage;
  }


  public String getNoDate() {
    return noDate;
  }

  public void setNoDate(String noDate) {
    this.noDate = noDate;
  }


  public String getNoPeople() {
    return noPeople;
  }

  public void setNoPeople(String noPeople) {
    this.noPeople = noPeople;
  }

}
