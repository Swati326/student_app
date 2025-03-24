package com.app.student.service;

import com.app.student.model.Student;
import com.app.student.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface StudentService{

    Teacher addStudentAgainstTeacherId(Integer teacherId, Student student);

    Set<Student> getStudentAgainstTeacherId(Integer teacherId);

    Set<Teacher> getTeacherAgainstStudentId(Integer studentId);


}
