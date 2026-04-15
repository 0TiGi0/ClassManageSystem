package com.xiaokui.controller;

import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;
import com.xiaokui.result.R;
import com.xiaokui.service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {

    public final ClassService classService;


    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/page-class")
    R<List<ClassTable>> getPageController(Integer page, Integer size) {
        return classService.getPage(page, size);
    }

    @PostMapping("/class")
    R<Object> addClassController(String name, Integer teacherId) {
        return classService.addClass(name, teacherId);
    }

    @DeleteMapping("/class")
    R<Object> removeClassByIdController(Integer id) {
        return classService.removeClassById(id);
    }

    @PutMapping("/class")
    R<Object> modifyClassByIdController(String name, Integer id, Integer teacherId) {
        return classService.modifyClassById(name, id, teacherId);
    }

    @GetMapping("/class")
    R<List<ClassTable>> getClassController(Integer id, String name) {
        return classService.getClass(id, name);
    }

    @GetMapping("/all_students")
    R<List<StudentOnlyIdName>> getAllStudentController(Integer id) {
        return classService.getAllStudent(id);
    }

    @GetMapping("/class_sum")
    R<Integer> getClassSumController() {
        return classService.getClassSum();
    }
}
