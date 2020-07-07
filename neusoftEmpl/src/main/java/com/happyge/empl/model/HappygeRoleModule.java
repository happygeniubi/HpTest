package com.happyge.empl.model;

import java.util.Date;

public class HappygeRoleModule {
    private Long id;

    private Date creation;

    private String label;

    private String modulecode;

    private Boolean finds;

    private Boolean adds;

    private Boolean deletes;

    private Boolean modifys;

    private String supercode;

    public HappygeRoleModule() {
    }
    public HappygeRoleModule(Date creation, String label, String moduleCode, boolean finds, boolean adds, boolean deletes, boolean modifys, String superCode) {
        this.creation = creation;
        this.label = label;
        this.modulecode = moduleCode;
        this.finds = finds;
        this.adds = adds;
        this.deletes = deletes;
        this.modifys = modifys;
        this.supercode = superCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getModulecode() {
        return modulecode;
    }

    public void setModulecode(String modulecode) {
        this.modulecode = modulecode == null ? null : modulecode.trim();
    }

    public Boolean getFinds() {
        return finds;
    }

    public void setFinds(Boolean finds) {
        this.finds = finds;
    }

    public Boolean getAdds() {
        return adds;
    }

    public void setAdds(Boolean adds) {
        this.adds = adds;
    }

    public Boolean getDeletes() {
        return deletes;
    }

    public void setDeletes(Boolean deletes) {
        this.deletes = deletes;
    }

    public Boolean getModifys() {
        return modifys;
    }

    public void setModifys(Boolean modifys) {
        this.modifys = modifys;
    }

    public String getSupercode() {
        return supercode;
    }

    public void setSupercode(String supercode) {
        this.supercode = supercode == null ? null : supercode.trim();
    }
}