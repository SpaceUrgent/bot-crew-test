package com.test.task.data.injector;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import com.test.task.entity.Degree;
import com.test.task.entity.Degree.DegreeName;
import com.test.task.entity.Department;
import com.test.task.entity.Lector;
import com.test.task.repository.DegreeRepository;
import com.test.task.repository.DepartmentRepository;
import com.test.task.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DummyDataInjector {
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private LectorRepository lectorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void init() {
        List<Degree> degrees = List.of(
                new Degree(DegreeName.ASSISTANT),
                new Degree(DegreeName.ASSOCIATE_PROFESSOR),
                new Degree(DegreeName.PROFESSOR)
        );
        degrees.forEach(degree -> degreeRepository.save(degree));
        degrees = degreeRepository.findAll();
        List<Lector> lectors = List.of(
                new Lector("Bob", "Thorton", degrees.get(0), BigDecimal.valueOf(150)),
                new Lector("John", "Kennedy", degrees.get(1), BigDecimal.valueOf(500)),
                new Lector("Elon", "Mask", degrees.get(2), BigDecimal.valueOf(1000)),
                new Lector("Henry", "Ford", degrees.get(0), BigDecimal.valueOf(200)),
                new Lector("Bill", "Clinton", degrees.get(1), BigDecimal.valueOf(400)),
                new Lector("John", "Cena", degrees.get(2), BigDecimal.valueOf(1000)),
                new Lector("Alice", "Alice", degrees.get(0), BigDecimal.valueOf(150)),
                new Lector("Ivan", "Ivanov", degrees.get(1), BigDecimal.valueOf(350)),
                new Lector("Gendalf", "White", degrees.get(2), BigDecimal.valueOf(900)),
                new Lector("Will", "Smith", degrees.get(0), BigDecimal.valueOf(200)),
                new Lector("George", "First", degrees.get(1), BigDecimal.valueOf(600)),
                new Lector("Warren", "Buffet", degrees.get(2), BigDecimal.valueOf(950)),
                new Lector("Eric", "Cartman", degrees.get(0), BigDecimal.valueOf(100)),
                new Lector("Ivan", "Ivanych", degrees.get(1), BigDecimal.valueOf(400)),
                new Lector("San", "Sanych", degrees.get(2), BigDecimal.valueOf(1100))
        );
        lectors.forEach(lector -> lectorRepository.save(lector));
        lectors = lectorRepository.findAll();
        List<Lector> scienceLectors = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            scienceLectors.add(lectors.get(i));
        }
        List<Lector> economicsLectors = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            economicsLectors.add(lectors.get(i));
        }
        List<Department> departments = List.of(
                new Department("Science", scienceLectors.get(2), scienceLectors),
                new Department("Economics", economicsLectors.get(2), economicsLectors)
        );
        departments.forEach(department -> departmentRepository.save(department));
    }
}
