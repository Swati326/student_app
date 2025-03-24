package com.app.student.service;

import ch.qos.logback.core.util.StringUtil;
import com.app.student.model.Student;
import com.app.student.model.Teacher;
import com.app.student.repo.StudentRepository;
import com.app.student.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher addStudentAgainstTeacherId(Integer teacherId, Student student) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        try {
            if (teacher.isPresent()) {
                Student student1 = studentRepository.findByName(student.getFullName());
                if (student1 != null) {
                    boolean b=checkIfStudentBelongWithSameTeacher(teacher.get(),student1);
                    if(b){
                        throw new RuntimeException("Student is already belongs with Given Teacher");
                    }else {
                        teacher.get().getStudentSet().add(student1);
                        student1.getTeacherSet().add(teacher.get());
                    }
                } else {
                    Student s = studentRepository.save(student);
                    teacher.get().getStudentSet().add(s);
                    s.getTeacherSet().add(teacher.get());
                }
                teacherRepository.save(teacher.get());
            } else {
                throw new RuntimeException("Teacher not found");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        return teacher.get();
    }

    @Override
    public Set<Student> getStudentAgainstTeacherId(Integer teacherId) {
        Set<Student> studentSet=null;
        Set<Student> studentSet1=null;
        Optional<Teacher> teacher=teacherRepository.findById(teacherId);
        if(teacher!=null) {
          studentSet1 = teacher.get().getStudentSet();
          //  studentSet = studentRepository.findStudentByTeacherId(teacher.get().getId());
        }
        return studentSet1;
    }

    @Override
    public Set<Teacher> getTeacherAgainstStudentId(Integer studentId) {

        Set<Teacher> teachers=null;
        Optional<Student> student=studentRepository.findById(studentId);
        if(student!=null)
        {
            teachers = student.get().getTeacherSet();
        }
        return teachers;
    }


    public boolean checkIfStudentBelongWithSameTeacher(Teacher teacher, Student student) {
        student = studentRepository.checkIfStudentBelongWithSameTeacher(teacher.getId(), student.getId());
        if (student != null) {
            return true;
        } else {
            return false;
        }
    }
}
