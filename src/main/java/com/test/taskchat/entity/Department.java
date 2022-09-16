package com.test.taskchat.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String departmentName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lector_id", unique = true)
    private Lector head;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Lector> lectors;
}
