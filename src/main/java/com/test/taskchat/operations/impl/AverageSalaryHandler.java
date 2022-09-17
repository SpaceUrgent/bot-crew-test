package com.test.taskchat.operations.impl;

import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.RequestHandler;
import com.test.taskchat.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AverageSalaryHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 43;
    private DepartmentService departmentService;

    @Autowired
    public AverageSalaryHandler(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX);
        List<Lector> lectors = departmentService.findByName(departmentName).getLectors();
        double averageSalary = getAverageSalary(lectors, departmentName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The average salary of ")
                .append(departmentName)
                .append(" is ")
                .append(averageSalary)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private double getAverageSalary(List<Lector> lectors, String departmentName) {
        return lectors
                .stream()
                .mapToDouble(lector ->
                        lector.getSalary().doubleValue())
                .average().orElseThrow(
                        () -> new RuntimeException("Can't get the average "
                                + "salary. There is no lector in department: " + departmentName)
                );
    }
}
