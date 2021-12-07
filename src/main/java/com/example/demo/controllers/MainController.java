package com.example.demo.controllers;

import com.example.demo.models.Lessons;
import com.example.demo.models.Student;
import com.example.demo.repositoryes.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    private final StudentRepository studentRepository;

    public MainController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "greeting";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(value = "id") long id, Model model) {
        if (!studentRepository.existsById(id)) {
            return "redirect:/";
        }
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.save(student);
        model.addAttribute("name", student.getName() + " " + student.getSurname());
        model.addAttribute("personId", student.getId());

        List<Lessons> date = student.getLessons();
        model.addAttribute("date", date);
        return "details";
    }

    @GetMapping("/details/{id}/{date}")
    public String lessons(@PathVariable(value = "id") long id, @PathVariable(value = "date") String date, Model model) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.save(student);
        model.addAttribute("name", student.getName() + " " + student.getSurname());

        List<Lessons> dateNumbers = student.getLessons();
        for (Lessons less : dateNumbers) {
            if (date.equals(less.getDate())) {
                model.addAttribute("date", less);
            }
        }
        return "lessons";
    }
}
