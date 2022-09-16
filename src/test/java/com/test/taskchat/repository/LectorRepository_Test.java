package com.test.taskchat.repository;

import com.test.taskchat.entity.Degree;
import com.test.taskchat.entity.Lector;
import com.test.taskchat.repository.DegreeRepository;
import com.test.taskchat.repository.LectorRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LectorRepository_Test {
    private static final String FIRST_NAME = "Bob";
    private static final String LAST_NAME = "Сruise";

    @Autowired
    private LectorRepository lectorRepository;
    @Autowired
    private DegreeRepository degreeRepository;

    @BeforeAll
    public void setup() {
        Degree professorDegree = new Degree();
        professorDegree.setDegreeName(Degree.DegreeName.PROFESSOR);
        professorDegree = degreeRepository.save(professorDegree);
        Lector bob = new Lector();
        bob.setFirstName(FIRST_NAME);
        bob.setLastName(LAST_NAME);
        bob.setDegree(professorDegree);
        bob.setSalary(BigDecimal.valueOf(1000));
        lectorRepository.save(bob);
    }

    @AfterAll
    public void close() {}

    @Test
    public void getLectorById_Ok() {
        Optional<Lector> actual = lectorRepository.findById(1L);
        assertTrue(actual.isPresent());
        assertTrue(FIRST_NAME.equals(actual.get().getFirstName()));
        assertTrue(LAST_NAME.equals(actual.get().getLastName()));
        assertEquals(Degree.DegreeName.PROFESSOR,
                actual.get().getDegree().getDegreeName());
        assertEquals(BigDecimal.valueOf(1000).stripTrailingZeros(),
                actual.get().getSalary().stripTrailingZeros());
    }
}
