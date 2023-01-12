package com.example.studentdemo.controller;


import com.example.studentdemo.entity.Student;
import com.example.studentdemo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository ;


    @PostMapping(path="/addStudent")
    public @ResponseBody String addStudent (@RequestParam String first_name,
                                            @RequestParam String last_name,
                                            @RequestParam String email,
                                            @RequestParam String course) {

        // @ResponseBody means the returned String
        // is the response, not a view name
        // @RequestParam means it is a parameter
        // from the GET or POST request
       Student student = new Student();
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setEmail(email);
        student.setCourse(course);
        studentRepository.save(student);
        return "Details got Saved";


        //sample postman request Http://localhost:8080/student/addStudent?first_name=Gerard&last_name=way&email=mcr@Gmail.com&course=Major in Music
    }


    @GetMapping(path="/getAllStudents")
    public @ResponseBody Iterable<Student> getAllStudents() {
        // This returns a JSON or
        // XML with the Book
        return studentRepository.findAll();
    }


    @GetMapping("/id")
    public  @ResponseBody Optional<Student> getById(@RequestParam long id){
        return  studentRepository.findById(id);

        //sample  postman request Http://localhost:8080/student/id?id=2
    }


}
