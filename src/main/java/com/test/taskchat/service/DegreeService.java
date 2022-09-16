package com.test.taskchat.service;

import com.test.taskchat.entity.Degree;

public interface DegreeService {
    Degree save(Degree degree);

    Degree findById(Long id);
}
