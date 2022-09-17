package com.test.task.operations.impl;

import java.util.List;
import com.test.task.operations.RequestHandler;
import com.test.task.service.LectorService;
import com.test.task.entity.Lector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameTemplateHandler implements RequestHandler {
    private static final int TEMPLATE_INDEX = 18;
    @Autowired
    private LectorService lectorService;


    @Override
    public String handle(String input) {
        String template = input.substring(TEMPLATE_INDEX);
        template = template.replaceAll("\\p{Punct}", "");
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
