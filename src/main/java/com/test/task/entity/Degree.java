package com.test.task.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DegreeName degreeName;

    public Degree(DegreeName degreeName) {
        this.degreeName = degreeName;
    }

    public enum DegreeName {
        ASSISTANT("assistant"),
        ASSOCIATE_PROFESSOR("associate professor"),
        PROFESSOR("professor");

        private String value;

        public String getValue() {
            return value;
        }

        DegreeName(String value) {
            this.value = value;
        }
    }
}
