package com.test.task.service;

import com.test.task.entity.Department;

public interface DepartmentService {
    Department save(Department department);

    Department findById(Long id);

    Department findByName(String departmentName);

    Department updateHead(Long departmentId, Long lectorId);

    void addLectorToDepartment(Long departmentId, Long lectorId);

    void removeLectorFromDepartment(Long departmentId, Long lectorId);
}
