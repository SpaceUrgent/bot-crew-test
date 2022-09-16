package com.test.taskchat.service;

import com.test.taskchat.entity.Lector;

public interface LectorService {
    Lector save(Lector lector);

    Lector findById(Long id);
}
