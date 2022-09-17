package com.test.task.repository;

import com.test.task.entity.Degree;
import com.test.task.entity.Lector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace= NONE)
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
        bob.setId(1L);
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

    @Test
    public void findByFirstOrLastNameLike_Ok() {
        List<Lector> actualOne = lectorRepository.
                findByFirstOrLastNameLike("ob");
        List<Lector> actualTwo = lectorRepository.
                findByFirstOrLastNameLike("rui");
        assertTrue(actualOne.size() == 1);
        assertTrue(actualTwo.size() == 1);
        assertEquals(FIRST_NAME, actualOne.get(0).getFirstName());
        assertEquals(LAST_NAME, actualOne.get(0).getLastName());
    }
}
