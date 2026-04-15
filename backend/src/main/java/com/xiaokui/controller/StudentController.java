package com.xiaokui.controller;

import com.xiaokui.dto.StudentTable;
import com.xiaokui.result.R;
import com.xiaokui.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/page-student")
    R<List<StudentTable>> getPageController(Integer page, Integer size) {
        return studentService.getPage(page, size);
    }

    @PostMapping("/student")
    R<Object> addStudentController(String name, Integer age, Boolean gender, Integer classId) {
        return studentService.addStudent(name, age, gender, classId);
    }

    @DeleteMapping("/student")
    R<Object> removeStudentByIdController(Integer id) {
        return studentService.removeStudentById(id);
    }

    @PutMapping("/student")
    R<Object> modifyStudentByIdController(Integer id, String name, Integer age, Boolean gender, Integer classId) {
        return studentService.modifyStudentById(id, name, age, gender, classId);
    }

    @GetMapping("/student")
    R<List<StudentTable>> getStudentController(Integer id, String name, String teacherName, String className) {
        return studentService.getStudent(id, name, teacherName, className);
    }

    @GetMapping("/student_sum")
    R<Integer> getStudentSumController() {
        return studentService.getStudentSum();
    }

}
