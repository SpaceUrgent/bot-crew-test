package com.test.taskchat.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DegreeName degreeName;

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
