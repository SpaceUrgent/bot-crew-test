package com.test.task.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public Department(String departmentName, Lector head, List<Lector> lectors) {
        this.departmentName = departmentName;
        this.head = head;
        this.lectors = lectors;
    }
}
