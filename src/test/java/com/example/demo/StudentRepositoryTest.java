package com.example.demo;

import com.example.demo.models.Student;
import com.example.demo.repositoryes.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findByName() throws Exception {
        List<Student> students = studentRepository.findByName("Andrii");
        assertThat(students).hasSize(1);
    }

    @Test
    public void deleteStudent() throws Exception{
        Student student = studentRepository.findBySurname("Steblin");
        studentRepository.delete(student);
        List<Student> studentList = studentRepository.findAll();

        assertThat(studentList).hasSize(1);
    }
}
