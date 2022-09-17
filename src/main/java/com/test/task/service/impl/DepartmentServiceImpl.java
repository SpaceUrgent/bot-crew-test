package com.test.task.service.impl;

import com.test.task.entity.Lector;
import com.test.task.repository.DepartmentRepository;
import com.test.task.repository.LectorRepository;
import com.test.task.service.DepartmentService;
import com.test.task.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LectorRepository lectorRepository;


    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find department with id " + id)
        );
    }

    @Override
    public Department findByName(String departmentName) {
        return departmentRepository.findByName(departmentName).orElseThrow(
                () -> new RuntimeException("Can't find department with name " + departmentName)
        );
    }

    @Override
    public Department updateHead(Long departmentId, Long lectorId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new RuntimeException("Can't update department with id "
                        + departmentId + ". Department wasn't found")
        );
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(
                () -> new RuntimeException("Can't set as head lector with id "
                        + lectorId + ". Lector wasn't found")
        );
        department.setHead(lector);
        return departmentRepository.save(department);
    }

    @Override
    public void addLectorToDepartment(Long departmentId, Long lectorId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new RuntimeException("Can't add lector to department with id "
                        + departmentId + ". Department wasn't found")
        );
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(
                () -> new RuntimeException("Can't add lector with id "
                        + lectorId + ". Lector wasn't found")
        );
        department.getLectors().add(lector);
        departmentRepository.save(department);
    }

    @Override
    public void removeLectorFromDepartment(Long departmentId, Long lectorId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new RuntimeException("Can't remove lector from department with id "
                        + departmentId + ". Department wasn't found")
        );
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(
                () -> new RuntimeException("Can't remove lector with id "
                        + lectorId + ". Lector wasn't found")
        );
        department.getLectors().remove(lector);
        departmentRepository.save(department);
    }
}
