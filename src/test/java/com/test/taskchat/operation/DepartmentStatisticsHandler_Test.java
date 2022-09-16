package com.test.taskchat.operation;

import com.test.taskchat.entity.Degree;
import com.test.taskchat.entity.Degree.DegreeName;
import com.test.taskchat.entity.Department;
import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.impl.DepartmentStatisticsHandler;
import com.test.taskchat.service.DepartmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class DepartmentStatisticsHandler_Test {
    private static final String INPUT = "Show Science statistics.";
    private static final String EXPECTED = "assistants - 3" + System.lineSeparator()
            + "associate professors - 4" + System.lineSeparator()
            + "professors - 3" + System.lineSeparator();
    private Department department;
    @InjectMocks
    private DepartmentStatisticsHandler departmentStatisticsHandler;
    @Mock
    private DepartmentService departmentService;

    @BeforeAll
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

    @AfterAll
    public void close() {}

    @Test
    public void handle_Ok() {
        Mockito.when(departmentService.findByName("Science")).thenReturn(department);
        String actual = departmentStatisticsHandler.handle(INPUT);
        Assertions.assertEquals(EXPECTED, actual);
    }
}
