package com.happyge.empl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HappygeRoleModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HappygeRoleModuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreationIsNull() {
            addCriterion("creation is null");
            return (Criteria) this;
        }

        public Criteria andCreationIsNotNull() {
            addCriterion("creation is not null");
            return (Criteria) this;
        }

        public Criteria andCreationEqualTo(Date value) {
            addCriterion("creation =", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationNotEqualTo(Date value) {
            addCriterion("creation <>", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationGreaterThan(Date value) {
            addCriterion("creation >", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationGreaterThanOrEqualTo(Date value) {
            addCriterion("creation >=", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationLessThan(Date value) {
            addCriterion("creation <", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationLessThanOrEqualTo(Date value) {
            addCriterion("creation <=", value, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationIn(List<Date> values) {
            addCriterion("creation in", values, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationNotIn(List<Date> values) {
            addCriterion("creation not in", values, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationBetween(Date value1, Date value2) {
            addCriterion("creation between", value1, value2, "creation");
            return (Criteria) this;
        }

        public Criteria andCreationNotBetween(Date value1, Date value2) {
            addCriterion("creation not between", value1, value2, "creation");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andModulecodeIsNull() {
            addCriterion("moduleCode is null");
            return (Criteria) this;
        }

        public Criteria andModulecodeIsNotNull() {
            addCriterion("moduleCode is not null");
            return (Criteria) this;
        }

        public Criteria andModulecodeEqualTo(String value) {
            addCriterion("moduleCode =", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeNotEqualTo(String value) {
            addCriterion("moduleCode <>", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeGreaterThan(String value) {
            addCriterion("moduleCode >", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeGreaterThanOrEqualTo(String value) {
            addCriterion("moduleCode >=", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeLessThan(String value) {
            addCriterion("moduleCode <", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeLessThanOrEqualTo(String value) {
            addCriterion("moduleCode <=", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeLike(String value) {
            addCriterion("moduleCode like", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeNotLike(String value) {
            addCriterion("moduleCode not like", value, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeIn(List<String> values) {
            addCriterion("moduleCode in", values, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeNotIn(List<String> values) {
            addCriterion("moduleCode not in", values, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeBetween(String value1, String value2) {
            addCriterion("moduleCode between", value1, value2, "modulecode");
            return (Criteria) this;
        }

        public Criteria andModulecodeNotBetween(String value1, String value2) {
            addCriterion("moduleCode not between", value1, value2, "modulecode");
            return (Criteria) this;
        }

        public Criteria andFindsIsNull() {
            addCriterion("finds is null");
            return (Criteria) this;
        }

        public Criteria andFindsIsNotNull() {
            addCriterion("finds is not null");
            return (Criteria) this;
        }

        public Criteria andFindsEqualTo(Boolean value) {
            addCriterion("finds =", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsNotEqualTo(Boolean value) {
            addCriterion("finds <>", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsGreaterThan(Boolean value) {
            addCriterion("finds >", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsGreaterThanOrEqualTo(Boolean value) {
            addCriterion("finds >=", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsLessThan(Boolean value) {
            addCriterion("finds <", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsLessThanOrEqualTo(Boolean value) {
            addCriterion("finds <=", value, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsIn(List<Boolean> values) {
            addCriterion("finds in", values, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsNotIn(List<Boolean> values) {
            addCriterion("finds not in", values, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsBetween(Boolean value1, Boolean value2) {
            addCriterion("finds between", value1, value2, "finds");
            return (Criteria) this;
        }

        public Criteria andFindsNotBetween(Boolean value1, Boolean value2) {
            addCriterion("finds not between", value1, value2, "finds");
            return (Criteria) this;
        }

        public Criteria andAddsIsNull() {
            addCriterion("adds is null");
            return (Criteria) this;
        }

        public Criteria andAddsIsNotNull() {
            addCriterion("adds is not null");
            return (Criteria) this;
        }

        public Criteria andAddsEqualTo(Boolean value) {
            addCriterion("adds =", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsNotEqualTo(Boolean value) {
            addCriterion("adds <>", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsGreaterThan(Boolean value) {
            addCriterion("adds >", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsGreaterThanOrEqualTo(Boolean value) {
            addCriterion("adds >=", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsLessThan(Boolean value) {
            addCriterion("adds <", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsLessThanOrEqualTo(Boolean value) {
            addCriterion("adds <=", value, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsIn(List<Boolean> values) {
            addCriterion("adds in", values, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsNotIn(List<Boolean> values) {
            addCriterion("adds not in", values, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsBetween(Boolean value1, Boolean value2) {
            addCriterion("adds between", value1, value2, "adds");
            return (Criteria) this;
        }

        public Criteria andAddsNotBetween(Boolean value1, Boolean value2) {
            addCriterion("adds not between", value1, value2, "adds");
            return (Criteria) this;
        }

        public Criteria andDeletesIsNull() {
            addCriterion("deletes is null");
            return (Criteria) this;
        }

        public Criteria andDeletesIsNotNull() {
            addCriterion("deletes is not null");
            return (Criteria) this;
        }

        public Criteria andDeletesEqualTo(Boolean value) {
            addCriterion("deletes =", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesNotEqualTo(Boolean value) {
            addCriterion("deletes <>", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesGreaterThan(Boolean value) {
            addCriterion("deletes >", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deletes >=", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesLessThan(Boolean value) {
            addCriterion("deletes <", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesLessThanOrEqualTo(Boolean value) {
            addCriterion("deletes <=", value, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesIn(List<Boolean> values) {
            addCriterion("deletes in", values, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesNotIn(List<Boolean> values) {
            addCriterion("deletes not in", values, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesBetween(Boolean value1, Boolean value2) {
            addCriterion("deletes between", value1, value2, "deletes");
            return (Criteria) this;
        }

        public Criteria andDeletesNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deletes not between", value1, value2, "deletes");
            return (Criteria) this;
        }

        public Criteria andModifysIsNull() {
            addCriterion("modifys is null");
            return (Criteria) this;
        }

        public Criteria andModifysIsNotNull() {
            addCriterion("modifys is not null");
            return (Criteria) this;
        }

        public Criteria andModifysEqualTo(Boolean value) {
            addCriterion("modifys =", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysNotEqualTo(Boolean value) {
            addCriterion("modifys <>", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysGreaterThan(Boolean value) {
            addCriterion("modifys >", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysGreaterThanOrEqualTo(Boolean value) {
            addCriterion("modifys >=", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysLessThan(Boolean value) {
            addCriterion("modifys <", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysLessThanOrEqualTo(Boolean value) {
            addCriterion("modifys <=", value, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysIn(List<Boolean> values) {
            addCriterion("modifys in", values, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysNotIn(List<Boolean> values) {
            addCriterion("modifys not in", values, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysBetween(Boolean value1, Boolean value2) {
            addCriterion("modifys between", value1, value2, "modifys");
            return (Criteria) this;
        }

        public Criteria andModifysNotBetween(Boolean value1, Boolean value2) {
            addCriterion("modifys not between", value1, value2, "modifys");
            return (Criteria) this;
        }

        public Criteria andSupercodeIsNull() {
            addCriterion("superCode is null");
            return (Criteria) this;
        }

        public Criteria andSupercodeIsNotNull() {
            addCriterion("superCode is not null");
            return (Criteria) this;
        }

        public Criteria andSupercodeEqualTo(String value) {
            addCriterion("superCode =", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeNotEqualTo(String value) {
            addCriterion("superCode <>", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeGreaterThan(String value) {
            addCriterion("superCode >", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeGreaterThanOrEqualTo(String value) {
            addCriterion("superCode >=", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeLessThan(String value) {
            addCriterion("superCode <", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeLessThanOrEqualTo(String value) {
            addCriterion("superCode <=", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeLike(String value) {
            addCriterion("superCode like", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeNotLike(String value) {
            addCriterion("superCode not like", value, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeIn(List<String> values) {
            addCriterion("superCode in", values, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeNotIn(List<String> values) {
            addCriterion("superCode not in", values, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeBetween(String value1, String value2) {
            addCriterion("superCode between", value1, value2, "supercode");
            return (Criteria) this;
        }

        public Criteria andSupercodeNotBetween(String value1, String value2) {
            addCriterion("superCode not between", value1, value2, "supercode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}