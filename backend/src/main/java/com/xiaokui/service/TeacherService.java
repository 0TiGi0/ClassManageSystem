package com.xiaokui.service;

import com.xiaokui.dto.TeacherTable;
import com.xiaokui.result.R;

import java.util.List;

public interface TeacherService {
    R<List<TeacherTable>> getPage(Integer page, Integer size);

    //可选参数:classId
    R<Object> addTeacher(String name, Integer age, Boolean gender, Integer classId);

    R<Object> removeTeacherById(Integer id);

    //可选参数:classId
    R<Object> modifyTeacherById(Integer id, String name, Integer age, Boolean gender, Integer classId);

    R<List<TeacherTable>> getTeacher(Integer id, String name, String className);

    R<Integer> getTeacherSum();
}
