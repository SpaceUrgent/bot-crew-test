package com.test.taskchat.operations.impl;

import com.test.taskchat.operations.RequestHandler;
import com.test.taskchat.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeCountHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 27;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeCountHandler(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX);
        int count = departmentService.findByName(departmentName).getLectors().size();
        return String.valueOf(count);
    }
}
