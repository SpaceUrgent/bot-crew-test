package com.test.task.service;

import java.util.List;
import com.test.task.entity.Lector;

public interface LectorService {
    Lector save(Lector lector);

    Lector findById(Long id);

    List<Lector> findWhereFirstOrLastNameLike(String pattern);
}
