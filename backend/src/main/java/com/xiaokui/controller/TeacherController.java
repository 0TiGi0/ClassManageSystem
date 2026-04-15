package com.xiaokui.controller;

import com.xiaokui.dto.TeacherTable;
import com.xiaokui.result.R;
import com.xiaokui.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    public final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/page-teacher")
    R<List<TeacherTable>> getPageController(Integer page, Integer size) {
        return teacherService.getPage(page, size);
    }

    @PostMapping("/teacher")
    R<Object> addTeacherController(String name, Integer age, Boolean gender, Integer classId) {
        return teacherService.addTeacher(name, age, gender, classId);
    }


    @DeleteMapping("/teacher")
    R<Object> removeTeacherByIdController(Integer id) {
        return teacherService.removeTeacherById(id);
    }

    @PutMapping("/teacher")
    R<Object> modifyTeacherByIdController(Integer id, String name, Integer age, Boolean gender, Integer classId) {
        return teacherService.modifyTeacherById(id, name, age, gender, classId);
    }

    @GetMapping("/teacher")
    R<List<TeacherTable>> getTeacherController(Integer id, String name, String className) {
        return teacherService.getTeacher(id, name, className);
    }

    @GetMapping("/teacher_sum")
    R<Integer> getTeacherSumController() {
        return teacherService.getTeacherSum();
    }

}
