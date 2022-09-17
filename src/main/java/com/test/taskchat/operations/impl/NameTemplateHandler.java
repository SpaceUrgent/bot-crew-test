package com.test.taskchat.operations.impl;

import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.RequestHandler;
import com.test.taskchat.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NameTemplateHandler implements RequestHandler {
    private static final int DEPARTMENT_NAME_INDEX = 17;
    private LectorService lectorService;

    @Autowired
    public NameTemplateHandler(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @Override
    public String handle(String input) {
        String template = input.substring(DEPARTMENT_NAME_INDEX);
        List<Lector> lectors = lectorService.findWhereFirstOrLastNameLike(template);
        if (lectors.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Lector lector : lectors) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(lector.getFirstName())
                    .append(" ")
                    .append(lector.getLastName());
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
