package com.xiaokui.controller;

import com.xiaokui.dto.ClassOnlyIdName;
import com.xiaokui.result.R;
import com.xiaokui.service.PublicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicController {

    public final PublicService publicService;

    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/hava_teacher_class")
    R<List<ClassOnlyIdName>> getClassHaveTeacherController() {
        return publicService.getClassHaveTeacher();
    }

    @GetMapping("/hava_not_teacher_class")
    R<List<ClassOnlyIdName>> getClassNoTeacherController() {
        return publicService.getClassNoTeacher();
    }
}
