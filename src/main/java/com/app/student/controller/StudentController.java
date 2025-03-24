package com.app.student.controller;


import com.app.student.model.Student;
import com.app.student.model.Teacher;
import com.app.student.service.StudentService;
import com.app.student.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentController {


    @Autowired
    StudentService studentService;


    @PostMapping("/addStudent/{teacherId}")
    ResponseEntity<Teacher> addStudentAgainstTeacherId(@PathVariable("teacherId") Integer teacherId, @RequestBody Student student)
    {
        Teacher teacher=studentService.addStudentAgainstTeacherId(teacherId,student);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/getStudent/{teacherId}")
    ResponseEntity<Set<Student>> addStudentAgainstTeacherId(@PathVariable("teacherId") Integer teacherId)
    {
        Set<Student> student1=studentService.getStudentAgainstTeacherId(teacherId);
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }


    @GetMapping("/getTeacher/{studentId}")
    ResponseEntity<Set<Teacher>> addTeachersAgainstTeacherId(@PathVariable("studentId") Integer studentId)
    {
        Set<Teacher> teachers=studentService.getTeacherAgainstStudentId(studentId);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }






}
