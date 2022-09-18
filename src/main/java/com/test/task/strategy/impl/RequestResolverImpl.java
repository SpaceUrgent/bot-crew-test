package com.test.task.strategy.impl;

import com.test.task.enums.Request;
import com.test.task.operations.RequestHandler;
import com.test.task.operations.impl.AverageSalaryHandler;
import com.test.task.operations.impl.DepartmentHeadHandler;
import com.test.task.operations.impl.DepartmentStatisticsHandler;
import com.test.task.operations.impl.EmployeeCountHandler;
import com.test.task.operations.impl.NameTemplateHandler;
import com.test.task.strategy.RequestStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RequestResolverImpl implements RequestStrategy {

    @Autowired
    private ApplicationContext context;

    @Override
    public RequestHandler getHandler(Request request) {
        switch (request) {
            case HEAD_OF_DEPARTMENT:
                return context.getBean(DepartmentHeadHandler.class);
            case STATISTICS:
                return context.getBean(DepartmentStatisticsHandler.class);
            case AVERAGE_SALARY:
                return context.getBean(AverageSalaryHandler.class);
            case COUNT_EMPLOYEE:
                return context.getBean(EmployeeCountHandler.class);
            case SEARCH_BY:
                return context.getBean(NameTemplateHandler.class);
        }
        return null;
    }
}
