package com.test.taskchat.repository;

import com.test.taskchat.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("from Lector l "
            + "left join fetch l.degree "
            + "where l.id = ?1")
    Optional<Lector> findById(Long aLong);
}