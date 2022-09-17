package com.test.task.operations.impl;

import java.util.List;
import com.test.task.entity.Lector;
import com.test.task.operations.RequestHandler;
import com.test.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AverageSalaryHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 44;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX);
        departmentName = departmentName.replaceAll("\\p{Punct}", "");
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
