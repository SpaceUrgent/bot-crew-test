package com.test.taskchat.operations.impl;

import com.test.taskchat.entity.Degree.DegreeName;
import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.OperationHandler;
import com.test.taskchat.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentStatisticsHandler implements OperationHandler {
    private static final String PLURAL_SUFFIX = "s";
    private static final String DELIMITER = " - ";
    private DepartmentService departmentService;

    @Autowired
    public DepartmentStatisticsHandler(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String handle(String input) {
        String departmentName = input.substring(input.indexOf(" ") + 1, input.lastIndexOf(" "));
        List<Lector> lectors = departmentService.findByName(departmentName).getLectors();
        StringBuilder stringBuilder = new StringBuilder();
        for (DegreeName degree : DegreeName.values()) {
            stringBuilder.append(degree.getValue())
                    .append(PLURAL_SUFFIX)
                    .append(DELIMITER)
                    .append(countByDegree(lectors, degree))
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private int countByDegree(List<Lector> lectors, DegreeName degreeName) {
        return (int) lectors.stream()
                .filter(lector ->
                        lector.getDegree().getDegreeName().name().equals(degreeName.name()))
                .count();
    }
}
