package com.happyge.empl.response;

/**
 *
 * 系统模块传输实体类
 *
 */

public class HappygeModuleResponse {
  private static final long serialVersionUID = -9080340785647580318L;

  private String name;
  private String code;
  private String superCode;
  private String page;
  private int level;

  public HappygeModuleResponse() {

  }

  public HappygeModuleResponse(String name,String code, String superCode, String page, int level) {
    super();
    this.code = code;
    this.superCode = superCode;
    this.name = name;
    this.page = page;
    this.level = level;
  }

  @Override
  public String toString() {
    return "HappygeModuleResponse [code=" + code + ", superCode=" + superCode
        + ", name=" + name + ", page=" + page + ", level=" + level
        + "]";
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSuperCode() {
    return superCode;
  }

  public void setSuperCode(String superCode) {
    this.superCode = superCode;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
