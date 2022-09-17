package com.test.task.operations.impl;

import java.util.List;
import com.test.task.entity.Degree.DegreeName;
import com.test.task.entity.Lector;
import com.test.task.operations.RequestHandler;
import com.test.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentStatisticsHandler implements RequestHandler {
    private static final String PLURAL_SUFFIX = "s";
    private static final String DELIMITER = " - ";
    private static final int DEPARTMENT_NAME_INDEX = 6;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public String handle(String input) {
        String departmentName = input.substring(DEPARTMENT_NAME_INDEX, input.lastIndexOf(" "));
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
