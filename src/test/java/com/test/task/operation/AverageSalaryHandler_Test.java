package com.test.task.operation;


import com.test.task.entity.Department;
import com.test.task.entity.Lector;
import com.test.task.operations.impl.AverageSalaryHandler;
import com.test.task.service.DepartmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AverageSalaryHandler_Test {
    private static final String INPUT = " Show the average salary for the department Science";
    private static final String EXPECTED = "The average salary of Science is 150.0" + System.lineSeparator();
    private static final String ERROR_MESSAGE = "Can't get the average "
            + "salary. There is no lector in department: Science";
    private Department department;
    private Department emptyDepartment;
    @InjectMocks
    private AverageSalaryHandler averageSalaryHandler;
    @Mock
    private DepartmentService departmentService;

    @BeforeAll
    public void setup() {
        List<Lector> lectors = new ArrayList<>();
        Lector lector;
        for (int i = 1; i <= 5; i++) {
            lector = new Lector();
            lector.setSalary(BigDecimal.valueOf(50 * i));
            lectors.add(lector);
        }
        department = new Department();
        department.setLectors(lectors);
        emptyDepartment = new Department();
        emptyDepartment.setLectors(new ArrayList<>());
    }

    @Test
    public void handle_Ok() {
        Mockito.when(departmentService.findByName("Science")).thenReturn(department);
        String actual = averageSalaryHandler.handle(INPUT);
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void handle_withEmptyDepartment_Throws() {
        Mockito.when(departmentService.findByName("Science")).thenReturn(emptyDepartment);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> averageSalaryHandler.handle(INPUT));
        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

}
