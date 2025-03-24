package com.app.student.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String fullName;


    @ManyToMany
    @JoinTable(name="student_teacher", joinColumns = @JoinColumn(name="student_id"),
    inverseJoinColumns = @JoinColumn(name="teacher_id"))
    Set<Teacher> teacherSet=new HashSet<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }
}
