package com.test.taskchat.service.impl;

import com.test.taskchat.entity.Lector;
import com.test.taskchat.repository.LectorRepository;
import com.test.taskchat.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LectorServiceImpl implements LectorService {
    private LectorRepository lectorRepository;

    @Autowired
    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public Lector save(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public Lector findById(Long id) {
        return lectorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find lector with id " + id)
        );
    }

    @Override
    public List<Lector> findWhereFirstOrLastNameLike(String pattern) {
        return lectorRepository.findByFirstOrLastNameLike(pattern);
    }
}
