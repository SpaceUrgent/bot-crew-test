package com.test.task.operation;

import com.test.task.entity.Department;
import com.test.task.entity.Lector;
import com.test.task.operations.impl.EmployeeCountHandler;
import com.test.task.service.DepartmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeCountHandler_Test {
    private static final String INPUT_SCIENCE = " Show count of employee for Science";
    private static final String INPUT_HISTORY = " Show count of employee for History";
    private static final String EXPECTED_SCIENCE = "10";
    private static final String EXPECTED_HISTORY = "0";

    private Department department;
    private Department emptyDepartment;
    @InjectMocks
    private EmployeeCountHandler employeeCountHandler;
    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        List<Lector> lectors = new ArrayList<>();
        Lector lector;
        for (int i = 0; i < 10; i++) {
            lector = new Lector();
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
        Mockito.when(departmentService.findByName("History")).thenReturn(emptyDepartment);
        String actualScience = employeeCountHandler.handle(INPUT_SCIENCE);
        String actualHistory = employeeCountHandler.handle(INPUT_HISTORY);
        assertEquals(EXPECTED_SCIENCE, actualScience);
        assertEquals(EXPECTED_HISTORY, actualHistory);
    }
}
