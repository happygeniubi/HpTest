package com.happyge.empl.model;

public class HllcPost {
    private String poid;

    private String poname;

    private String podate;

    private String popeople;

    private String podepartment;

    private String pomessage;

    private String deid;

    public String getPoid() {
        return poid;
    }

    public void setPoid(String poid) {
        this.poid = poid == null ? null : poid.trim();
    }

    public String getPoname() {
        return poname;
    }

    public void setPoname(String poname) {
        this.poname = poname == null ? null : poname.trim();
    }

    public String getPodate() {
        return podate;
    }

    public void setPodate(String podate) {
        this.podate = podate == null ? null : podate.trim();
    }

    public String getPopeople() {
        return popeople;
    }

    public void setPopeople(String popeople) {
        this.popeople = popeople == null ? null : popeople.trim();
    }

    public String getPodepartment() {
        return podepartment;
    }

    public void setPodepartment(String podepartment) {
        this.podepartment = podepartment == null ? null : podepartment.trim();
    }

    public String getPomessage() {
        return pomessage;
    }

    public void setPomessage(String pomessage) {
        this.pomessage = pomessage == null ? null : pomessage.trim();
    }

    public String getDeid() {
        return deid;
    }

    public void setDeid(String deid) {
        this.deid = deid == null ? null : deid.trim();
    }
}