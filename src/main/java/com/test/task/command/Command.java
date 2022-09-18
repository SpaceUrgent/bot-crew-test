package com.test.task.command;

import com.test.task.enums.Request;
import com.test.task.strategy.RequestStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Component
public class Command implements Callable<Integer> {
    @Autowired
    private RequestStrategy requestDispatcher;

    @Option(names = "-i",
            description = "The input for execution",
            required = true)
    String input;


    @Override
    public Integer call() throws Exception {
        try {
            Request request = Request.ifContains(input);
            if (request == null) {
                throw new RuntimeException("Unknown command: " + input);
            }
            System.out.println("Output: " + requestDispatcher.getHandler(request).handle(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
