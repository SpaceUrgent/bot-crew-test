package com.test.taskchat.operations.impl;

import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.OperationHandler;
import com.test.taskchat.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentHeadHandler implements OperationHandler {
    private static final int DEPARTMENT_NAME_START = 26;
    private DepartmentService departmentService;

    @Autowired
    public DepartmentHeadHandler(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_START);
        Lector lector = departmentService.findByName(departmentName).getHead();
        String headFirstName = lector.getFirstName();
        String headLastName = lector.getLastName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Head of ")
                .append(departmentName)
                .append(" department is ")
                .append(headFirstName)
                .append(" ")
                .append(headLastName)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
