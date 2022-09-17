package com.test.task.strategy.impl;

import com.test.task.operations.impl.AverageSalaryHandler;
import com.test.task.operations.impl.DepartmentHeadHandler;
import com.test.task.operations.impl.DepartmentStatisticsHandler;
import com.test.task.operations.impl.EmployeeCountHandler;
import com.test.task.operations.impl.NameTemplateHandler;
import com.test.task.strategy.RequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RequestDispatcherImpl implements RequestDispatcher {
    private static final String HEAD_OF_DEPARTMENT = "head of department";
    private static final String STATISTICS = "statistics";
    private static final String AVERAGE_SALARY = "average salary";
    private static final String COUNT_EMPLOYEE = "count of employee";
    private static final String SEARCH_BY = "search by";
    private static final String CLOSE = " close";
    @Autowired
    private ApplicationContext context;

    @Override
    public String resolve(Object input) {
        switch (input) {
            case String s && s.contains(HEAD_OF_DEPARTMENT):
                return context.getBean(DepartmentHeadHandler.class).handle((String) input);
            case String s && s.contains(STATISTICS):
                return context.getBean(DepartmentStatisticsHandler.class).handle((String) input);
            case String s && s.contains(AVERAGE_SALARY):
                return context.getBean(AverageSalaryHandler.class).handle((String) input);
            case String s && s.contains(COUNT_EMPLOYEE):
                return context.getBean(EmployeeCountHandler.class).handle((String) input);
            case String s && s.contains(SEARCH_BY):
                return context.getBean(NameTemplateHandler.class).handle((String) input);
            case String s && s.equalsIgnoreCase(CLOSE):
                return "Shut down";
            default:
                throw new RuntimeException("Unknown command: " + input);
        }
    }
}
