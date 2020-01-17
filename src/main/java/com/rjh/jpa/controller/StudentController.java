package com.rjh.jpa.controller;

import com.rjh.jpa.entity.Student;
import com.rjh.jpa.exception.ResourceNotExistException;
import com.rjh.jpa.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Null
 * @date 2020-01-13
 */
@RestController
@RequestMapping("students")
public class StudentController {

    @Resource
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> listStudents(){
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentRepository.saveAndFlush(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student){
        if(student.getId()!=null && studentRepository.existsById(student.getId())){
//        if(student.getId()!=null && studentRepository.findById(student.getId()).isPresent()){
//        if(student.getId()!=null && studentRepository.getOne(student.getId())!=null){
            Student newStudent=studentRepository.saveAndFlush(student);
            return studentRepository.getOne(student.getId());
        }else{
            throw new ResourceNotExistException();
        }
    }

    @GetMapping("{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        return studentRepository.findById(id);
    }
}
