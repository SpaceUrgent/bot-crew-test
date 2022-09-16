package com.test.taskchat.service;

import com.test.taskchat.entity.Department;
import com.test.taskchat.entity.Lector;

public interface DepartmentService {
    Department save(Department department);
    Department findById(Long id);
    Department findByName(String departmentName);
    Department updateHead(Long departmentId, Long lectorId);
    void addLectorToDepartment(Long departmentId, Long lectorId);
    void removeLectorFromDepartment(Long departmentId, Long lectorId);
}
