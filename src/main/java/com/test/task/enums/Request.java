package com.test.task.enums;

public enum Request {
    HEAD_OF_DEPARTMENT("head of department"),
    STATISTICS("statistics"),
    AVERAGE_SALARY("average salary"),
    COUNT_EMPLOYEE("count of employee"),
    SEARCH_BY("search by"),
    CLOSE(" close");

    private String value;

    Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Request ifContains(String input) {
        for (Request request : values()) {
            if (input.contains(request.getValue())) {
                return request;
            }
        }
        return null;
    }
}
