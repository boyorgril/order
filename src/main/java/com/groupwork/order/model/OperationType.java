package com.groupwork.order.model;

public enum OperationType {
    /**
     * 操作类型
     */
    SELECT("select"),
    UNKNOWN("unknown"),
    DELETE("delete"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}
