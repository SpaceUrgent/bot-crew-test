package com.test.task.operation;

import com.test.task.entity.Degree;
import com.test.task.entity.Degree.DegreeName;
import com.test.task.entity.Department;
import com.test.task.entity.Lector;
import com.test.task.operations.impl.DepartmentStatisticsHandler;
import com.test.task.service.DepartmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentStatisticsHandler_Test {
    private static final String INPUT = " Show Science statistics.";
    private static final String EXPECTED = "assistants - 3" + System.lineSeparator()
            + "associate professors - 4" + System.lineSeparator()
            + "professors - 3" + System.lineSeparator();
    private Department department;
    @InjectMocks
    private DepartmentStatisticsHandler departmentStatisticsHandler;
    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        List<Lector> lectors = new ArrayList<>();
        Lector lector;
        List<Degree> degrees = new ArrayList<>();
        Degree degree;
        for (int i = 0; i < DegreeName.values().length; i++) {
            degree = new Degree();
            degree.setDegreeName(DegreeName.values()[i]);
            degrees.add(degree);
        }
        for (int i = 0; i < 10; i++) {
            lector = new Lector();
            lector.setFirstName("Name " + i);
            lector.setLastName("Surname " + i);
            lector.setDegree(
                    (i <= 2 ? degrees.get(0) :
                            (i <= 6) ? degrees.get(1) : degrees.get(2))
            );
            lector.setSalary(BigDecimal.valueOf(i * 10));
            lectors.add(lector);
        }
        department = new Department();
        department.setDepartmentName("Science");
        department.setHead(lectors.get(0));
        department.setLectors(lectors);
    }

    @Test
    public void handle_Ok() {
        Mockito.when(departmentService.findByName("Science")).thenReturn(department);
        String actual = departmentStatisticsHandler.handle(INPUT);
        Assertions.assertEquals(EXPECTED, actual);
    }
}
