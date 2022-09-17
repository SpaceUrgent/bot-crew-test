package com.test.task;

import com.test.task.runner.Command;
import com.test.task.strategy.RequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.spring.PicocliSpringFactory;

import java.util.Scanner;

@SpringBootApplication
public class TestTaskApplication implements CommandLineRunner, ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}
	private PicocliSpringFactory factory;
	private Command command;
	private int exitCode;

	@Autowired
	public TestTaskApplication(PicocliSpringFactory factory, Command command) {
		this.factory = factory;
		this.command = command;
	}

	@Autowired
	private RequestDispatcher requestDispatcher;

	@Override
	public void run(String... args) throws Exception {
		String input = "";
		while (!input.equalsIgnoreCase("close")) {
			System.out.println("Please enter input here. To shut down the application type 'Close'");
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextLine();
			exitCode = new CommandLine(command, factory).execute("-i " + input);
		}
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}
}