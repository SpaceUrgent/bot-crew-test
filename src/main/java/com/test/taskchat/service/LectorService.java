package com.test.taskchat.service;

import com.test.taskchat.entity.Lector;

import java.util.List;

public interface LectorService {
    Lector save(Lector lector);

    Lector findById(Long id);

    List<Lector> findWhereFirstOrLastNameLike(String pattern);
}
