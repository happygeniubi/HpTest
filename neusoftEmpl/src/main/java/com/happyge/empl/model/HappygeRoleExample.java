package com.happyge.empl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HappygeRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HappygeRoleExample() {
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

        public Criteria andTimestampIsNull() {
            addCriterion("timestamp is null");
            return (Criteria) this;
        }

        public Criteria andTimestampIsNotNull() {
            addCriterion("timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andTimestampEqualTo(Date value) {
            addCriterion("timestamp =", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotEqualTo(Date value) {
            addCriterion("timestamp <>", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThan(Date value) {
            addCriterion("timestamp >", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("timestamp >=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThan(Date value) {
            addCriterion("timestamp <", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThanOrEqualTo(Date value) {
            addCriterion("timestamp <=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampIn(List<Date> values) {
            addCriterion("timestamp in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotIn(List<Date> values) {
            addCriterion("timestamp not in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampBetween(Date value1, Date value2) {
            addCriterion("timestamp between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotBetween(Date value1, Date value2) {
            addCriterion("timestamp not between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNull() {
            addCriterion("roleName is null");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNotNull() {
            addCriterion("roleName is not null");
            return (Criteria) this;
        }

        public Criteria andRolenameEqualTo(String value) {
            addCriterion("roleName =", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotEqualTo(String value) {
            addCriterion("roleName <>", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThan(String value) {
            addCriterion("roleName >", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThanOrEqualTo(String value) {
            addCriterion("roleName >=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThan(String value) {
            addCriterion("roleName <", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThanOrEqualTo(String value) {
            addCriterion("roleName <=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLike(String value) {
            addCriterion("roleName like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotLike(String value) {
            addCriterion("roleName not like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameIn(List<String> values) {
            addCriterion("roleName in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotIn(List<String> values) {
            addCriterion("roleName not in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameBetween(String value1, String value2) {
            addCriterion("roleName between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotBetween(String value1, String value2) {
            addCriterion("roleName not between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionIsNull() {
            addCriterion("roleDescription is null");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionIsNotNull() {
            addCriterion("roleDescription is not null");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionEqualTo(String value) {
            addCriterion("roleDescription =", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionNotEqualTo(String value) {
            addCriterion("roleDescription <>", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionGreaterThan(String value) {
            addCriterion("roleDescription >", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("roleDescription >=", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionLessThan(String value) {
            addCriterion("roleDescription <", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionLessThanOrEqualTo(String value) {
            addCriterion("roleDescription <=", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionLike(String value) {
            addCriterion("roleDescription like", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionNotLike(String value) {
            addCriterion("roleDescription not like", value, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionIn(List<String> values) {
            addCriterion("roleDescription in", values, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionNotIn(List<String> values) {
            addCriterion("roleDescription not in", values, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionBetween(String value1, String value2) {
            addCriterion("roleDescription between", value1, value2, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRoledescriptionNotBetween(String value1, String value2) {
            addCriterion("roleDescription not between", value1, value2, "roledescription");
            return (Criteria) this;
        }

        public Criteria andRolelabelIsNull() {
            addCriterion("roleLabel is null");
            return (Criteria) this;
        }

        public Criteria andRolelabelIsNotNull() {
            addCriterion("roleLabel is not null");
            return (Criteria) this;
        }

        public Criteria andRolelabelEqualTo(String value) {
            addCriterion("roleLabel =", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelNotEqualTo(String value) {
            addCriterion("roleLabel <>", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelGreaterThan(String value) {
            addCriterion("roleLabel >", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelGreaterThanOrEqualTo(String value) {
            addCriterion("roleLabel >=", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelLessThan(String value) {
            addCriterion("roleLabel <", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelLessThanOrEqualTo(String value) {
            addCriterion("roleLabel <=", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelLike(String value) {
            addCriterion("roleLabel like", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelNotLike(String value) {
            addCriterion("roleLabel not like", value, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelIn(List<String> values) {
            addCriterion("roleLabel in", values, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelNotIn(List<String> values) {
            addCriterion("roleLabel not in", values, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelBetween(String value1, String value2) {
            addCriterion("roleLabel between", value1, value2, "rolelabel");
            return (Criteria) this;
        }

        public Criteria andRolelabelNotBetween(String value1, String value2) {
            addCriterion("roleLabel not between", value1, value2, "rolelabel");
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

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
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