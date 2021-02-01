package com.example.springdataredis.repository;

import com.example.springdataredis.domain.Gender;
import com.example.springdataredis.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void test() {
        Student student = new Student("Eng2015001", "John Doe", Gender.MALE, 1);
        studentRepository.save(student);

        Student retrievedStudent = studentRepository.findById("Eng2015001").get();

        retrievedStudent.setName("Richard Watson");
        studentRepository.save(student);

        studentRepository.deleteById(student.getId());

        Student engStudent = new Student("Eng2015001", "John Doe", Gender.MALE, 1);
        Student medStudent = new Student("Med2015001", "Gareth Houston", Gender.MALE, 2);
        studentRepository.save(engStudent);
        studentRepository.save(medStudent);

        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);

        for (Student student1 : students) {
            System.out.println(student.toString());
        }
    }
}