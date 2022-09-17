package com.test.task.service.impl;

import java.util.List;
import com.test.task.entity.Lector;
import com.test.task.repository.LectorRepository;
import com.test.task.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectorServiceImpl implements LectorService {
    @Autowired
    private LectorRepository lectorRepository;

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
