package com.test.task.operations.impl;

import com.test.task.operations.RequestHandler;
import com.test.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCountHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 28;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX);
        departmentName = departmentName.replaceAll("\\p{Punct}", "");
        int count = departmentService.findByName(departmentName).getLectors().size();
        return String.valueOf(count);
    }
}
