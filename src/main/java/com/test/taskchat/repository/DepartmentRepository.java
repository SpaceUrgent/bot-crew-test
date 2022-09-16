package com.test.taskchat.repository;

import com.test.taskchat.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("from Department d "
            + "left join fetch d.head h "
            + "left join fetch h.degree "
            + "left join fetch d.lectors l "
            + "left join fetch l.degree "
            + "where d.id = ?1")
    Optional<Department> findById(Long id);

    @Query("from Department d "
            + "left join fetch d.head h "
            + "left join fetch h.degree "
            + "left join fetch d.lectors l "
            + "left join fetch l.degree "
            + "where d.departmentName = ?1")
    Optional<Department> findByName(String departmentName);
}
