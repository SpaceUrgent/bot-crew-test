package com.test.taskchat.repository;

import com.test.taskchat.entity.Degree;
import com.test.taskchat.entity.Department;
import com.test.taskchat.entity.Lector;
import com.test.taskchat.repository.DegreeRepository;
import com.test.taskchat.repository.DepartmentRepository;
import com.test.taskchat.repository.LectorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentRepository_Test {
    private static final String FIRST_NAME_BOB = "Bob";
    private static final String LAST_NAME_BOB = "Cruise";
    private static final String FIRST_NAME_ALICE = "Alice";
    private static final String LAST_NAME_ALICE = "Johnson";
    private static final String DEPARTMENT_NAME = "Science";

    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private LectorRepository lectorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeAll
    public void setup() {
        Degree professorDegree = new Degree();
        professorDegree.setDegreeName(Degree.DegreeName.PROFESSOR);
        Degree assistantDegree = new Degree();
        assistantDegree.setDegreeName(Degree.DegreeName.ASSISTANT);
        professorDegree = degreeRepository.save(professorDegree);
        assistantDegree = degreeRepository.save(assistantDegree);
        Lector bob = new Lector();
        bob.setFirstName(FIRST_NAME_BOB);
        bob.setLastName(LAST_NAME_BOB);
        bob.setDegree(professorDegree);
        bob.setSalary(BigDecimal.valueOf(1000));
        Lector alice = new Lector();
        alice.setFirstName(FIRST_NAME_ALICE);
        alice.setLastName(LAST_NAME_ALICE);
        alice.setDegree(assistantDegree);
        alice.setSalary(BigDecimal.valueOf(1100));
        Lector bobFromDb = lectorRepository.save(bob);
        Lector aliceFromDb = lectorRepository.save(alice);
        Department scienceDepartment = new Department();
        scienceDepartment.setDepartmentName(DEPARTMENT_NAME);
        scienceDepartment.setHead(bobFromDb);
        scienceDepartment.setLectors(List.of(bobFromDb, aliceFromDb));
        departmentRepository.save(scienceDepartment);
    }

    @AfterAll
    public void close() {}

    @Test
    public void findById_Ok() {
        Optional<Department> actual = departmentRepository.findById(1L);
        assertTrue(actual.isPresent());
        assertEquals(DEPARTMENT_NAME, actual.get().getDepartmentName());
        assertEquals(FIRST_NAME_BOB, actual.get().getHead().getFirstName());
        assertTrue(actual.get().getLectors().size() == 2);
        assertTrue(actual.get().getLectors()
                .stream()
                .map(Lector::getFirstName)
                .collect(Collectors.toList()).containsAll(List.of(FIRST_NAME_BOB, FIRST_NAME_ALICE))
        );
    }

    @Test
    public void findByName_Ok() {
        Optional<Department> actual = departmentRepository.findByName(DEPARTMENT_NAME);
        assertTrue(actual.isPresent());
        assertEquals(DEPARTMENT_NAME, actual.get().getDepartmentName());
        assertEquals(FIRST_NAME_BOB, actual.get().getHead().getFirstName());
        assertTrue(actual.get().getLectors().size() == 2);
        assertTrue(actual.get().getLectors()
                .stream()
                .map(Lector::getFirstName)
                .collect(Collectors.toList()).containsAll(List.of(FIRST_NAME_BOB, FIRST_NAME_ALICE))
        );
    }
}
