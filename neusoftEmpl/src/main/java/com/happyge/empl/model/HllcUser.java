package com.happyge.empl.model;
import java.io.Serializable;


public class HllcUser implements Serializable {
    private String uid;
    private String uname;
    private String upassword;
    private String upetname;
    private String ustatus;
    private String ucreatetime;
    private String usalt;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUpetname() {
        return upetname;
    }

    public void setUpetname(String upetname) {
        this.upetname = upetname;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUcreatetime() {
        return ucreatetime;
    }

    public void setUcreatetime(String ucreatetime) {
        this.ucreatetime = ucreatetime;
    }

    public String getUsalt() {
        return usalt;
    }

    public void setUsalt(String usalt) {
        this.usalt = usalt;
    }

    @Override
    public String toString() {
        return "HllcUser{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", upetname='" + upetname + '\'' +
                ", ustatus='" + ustatus + '\'' +
                ", ucreatetime='" + ucreatetime + '\'' +
                ", usalt='" + usalt + '\'' +
                '}';
    }
}
