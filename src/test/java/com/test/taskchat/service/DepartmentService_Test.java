package com.test.taskchat.service;

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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentService_Test {
    private static final String FIRST_NAME_BOB = "Bob";
    private static final String LAST_NAME_BOB = "Cruise";
    private static final String FIRST_NAME_ALICE = "Alice";
    private static final String LAST_NAME_ALICE = "Johnson";
    private static final String FIRST_NAME_JOHN = "John";
    private static final String LAST_NAME_JOHN = "Cena";
    private static final String DEPARTMENT_NAME = "Science";
    private Degree professorDegree;
    private Lector john;

    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private LectorRepository lectorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    @BeforeAll
    public void setup() {
        professorDegree = new Degree();
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
        john = new Lector();
        john.setFirstName(FIRST_NAME_JOHN);
        john.setLastName(LAST_NAME_JOHN);
        john.setDegree(professorDegree);
        john.setSalary(BigDecimal.valueOf(1000));
        john = lectorRepository.save(john);
    }

    @AfterAll
    public void close() {}

    @Test
    @Order(1)
    public void addLectorToDepartment_Ok() {
        departmentService.addLectorToDepartment(1L, john.getId());
        Department department = departmentService.findById(1L);
        assertTrue(department.getLectors().size() == 3);
        assertTrue(department.getLectors()
                .stream()
                .map(Lector::getFirstName)
                .collect(Collectors.toList()).contains(FIRST_NAME_JOHN)
        );
    }

    @Test
    @Order(2)
    public void removeLectorFromDepartment_Ok() {
        departmentService.removeLectorFromDepartment(1L, 2L);
        Department department = departmentService.findById(1L);
        assertTrue(department.getLectors().size() == 2);
        assertTrue(!department.getLectors()
                .stream()
                .map(Lector::getFirstName)
                .collect(Collectors.toList()).contains(FIRST_NAME_ALICE)
        );
    }

    @Test
    @Order(3)
    public void updateHead() {
        departmentService.updateHead(1L, john.getId());
        Department department = departmentService.findById(1L);
        assertTrue(department.getHead().getFirstName().equals(FIRST_NAME_JOHN));
    }

    @Test
    @Order(4)
    public void findById_NonExistingId_Throws() {
        RuntimeException actual = assertThrows(RuntimeException.class,
                () -> departmentService.findById(100L));
        assertEquals("Can't find department with id 100", actual.getMessage());
    }

    @Test
    @Order(5)
    public void findByName_NonExistingName_Throws() {
        RuntimeException actual = assertThrows(RuntimeException.class,
                () -> departmentService.findByName("History"));
        assertEquals("Can't find department with name History", actual.getMessage());
    }

    @Test
    @Order(6)
    public void updateHead_NonExistingIds_Throws() {
        RuntimeException wrongDepIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.updateHead(100L, 1L));
        assertEquals("Can't update department with id 100. Department wasn't found",
                wrongDepIdActual.getMessage());
        RuntimeException wrongLectorIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.updateHead(1L, 100L));
        assertEquals("Can't set as head lector with id 100. Lector wasn't found",
                wrongLectorIdActual.getMessage());
    }

    @Test
    @Order(7)
    public void addLectorToDepartment_NonExistingIds_Throws() {
        RuntimeException wrongDepIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.addLectorToDepartment(100L, 1L));
        assertEquals("Can't add lector to department with id 100. Department wasn't found",
                wrongDepIdActual.getMessage());
        RuntimeException wrongLectorIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.addLectorToDepartment(1L, 100L));
        assertEquals("Can't add lector with id 100. Lector wasn't found",
                wrongLectorIdActual.getMessage());
    }

    @Test
    @Order(8)
    public void removeLectorFromDepartment_NonExistingIds_Throws() {
        RuntimeException wrongDepIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.removeLectorFromDepartment(100L, 1L));
        assertEquals("Can't remove lector from department with id 100. Department wasn't found",
                wrongDepIdActual.getMessage());
        RuntimeException wrongLectorIdActual = assertThrows(RuntimeException.class,
                () -> departmentService.removeLectorFromDepartment(1L, 100L));
        assertEquals("Can't remove lector with id 100. Lector wasn't found",
                wrongLectorIdActual.getMessage());
    }
}
