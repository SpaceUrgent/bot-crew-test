package com.test.task.strategy;

import com.test.task.enums.Request;
import com.test.task.operations.RequestHandler;

public interface RequestStrategy {
    RequestHandler getHandler(Request operation);
}
