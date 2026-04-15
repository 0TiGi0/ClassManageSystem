package com.xiaokui.service;

import com.xiaokui.dto.TeacherTable;

import java.util.List;

public interface TeacherService {
    List<TeacherTable> getPage(Integer page, Integer size);

    //可选参数:classId
    boolean addTeacher(String name, Integer age, Boolean gender, Integer classId);

    Boolean removeTeacherById(Integer id);

    //可选参数:classId
    Boolean modifyTeacherById(Integer id, String name, Integer age, Boolean gender, Integer classId);

    List<TeacherTable> getTeacher(Integer id, String name, String className);
}
