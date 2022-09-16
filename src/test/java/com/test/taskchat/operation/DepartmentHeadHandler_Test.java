package com.test.taskchat.operation;

import com.test.taskchat.entity.Degree;
import com.test.taskchat.entity.Department;
import com.test.taskchat.entity.Lector;
import com.test.taskchat.operations.impl.DepartmentHeadHandler;
import com.test.taskchat.service.DepartmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class DepartmentHeadHandler_Test {
    private static final String INPUT = "Who is head of department Science";
    private static final String EXPECTED = "Head of Science department is Bob Cruise"
            + System.lineSeparator();
    private Department department;
    @InjectMocks
    private DepartmentHeadHandler departmentHeadHandler;
    @Mock
    private DepartmentService departmentService;

    @BeforeAll
    public void setup() {
        Degree professorDegree = new Degree();
        professorDegree.setDegreeName(Degree.DegreeName.PROFESSOR);
        Lector bob = new Lector();
        bob.setFirstName("Bob");
        bob.setLastName("Cruise");
        bob.setDegree(professorDegree);
        bob.setSalary(BigDecimal.valueOf(1000));
        department = new Department();
        department.setDepartmentName("Science");
        department.setHead(bob);
        department.setLectors(List.of(bob));
    }

    @AfterAll
    public void close() {}

    @Test
    public void handle_Ok() {
        Mockito.when(departmentService.findByName("Science")).thenReturn(department);
        String actual = departmentHeadHandler.handle(INPUT);
        Assertions.assertEquals(EXPECTED, actual);
    }

}
