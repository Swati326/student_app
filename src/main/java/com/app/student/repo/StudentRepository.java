package com.app.student.repo;

import com.app.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Query(value = "select * from student where full_name like %:fullName%",nativeQuery = true)
    Student findByName(@Param("fullName") String fullName);

    @Query(value = "SELECT s.* FROM student s INNER JOIN student_teacher st ON s.id = st.student_id WHERE st.student_id = :studentId AND st.teacher_id = :teacherId",nativeQuery = true)
    Student checkIfStudentBelongWithSameTeacher(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    @Query(value = "select * from student s inner join student_teacher st on s.id=st.student_id where st.teacher_id = :teacherId ",nativeQuery = true)
    Set<Student> findStudentByTeacherId(@Param("teacherId") Integer teacherId);
}
