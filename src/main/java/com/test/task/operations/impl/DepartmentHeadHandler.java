package com.test.task.operations.impl;

import com.test.task.entity.Lector;
import com.test.task.operations.RequestHandler;
import com.test.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DepartmentHeadHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 27;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX);
        departmentName = departmentName.replaceAll("\\p{Punct}", "");
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
