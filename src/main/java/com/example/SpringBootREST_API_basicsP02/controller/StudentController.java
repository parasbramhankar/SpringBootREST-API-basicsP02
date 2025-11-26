package com.example.SpringBootREST_API_basicsP02.controller;

import com.example.SpringBootREST_API_basicsP02.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    Map<Integer, Student>studentDB=new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Student>>getAllBooks(){
        return ResponseEntity.ok(new ArrayList<>(studentDB.values()));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        studentDB.put(student.getId(), student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student>getStudentById(@PathVariable int id){
        Student student=studentDB.get(id);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>updateStudent(@PathVariable int id, @RequestBody Student student){
        Student existing=studentDB.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        studentDB.put(id,student);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/age")
    public ResponseEntity<Student>UpdateStudentAge(@PathVariable int id, @RequestBody int  newAge){
        Student student=studentDB.get(id);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        student.setAge(newAge);
        studentDB.put(student.getId(),student);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteStudent(@PathVariable int id){
        Student student=studentDB.remove(id);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
