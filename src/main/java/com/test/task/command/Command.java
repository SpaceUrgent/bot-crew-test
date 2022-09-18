package com.test.task.command;

import com.test.task.strategy.RequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Component
public class Command implements Callable<Integer> {
    @Autowired
    private RequestResolver requestDispatcher;

    @Option(names = "-i",
            description = "The input for execution",
            required = true)
    String input;


    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Output: " + requestDispatcher.resolve(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
