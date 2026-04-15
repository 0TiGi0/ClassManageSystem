package com.xiaokui.service;

import com.xiaokui.dto.StudentTable;

import java.util.List;

public interface StudentService {
    List<StudentTable> getPage(Integer page, Integer size);

    //可选参数:classId
    boolean addStudent(String name, Integer age, Boolean gender, Integer classId);

    Boolean removeStudentById(Integer id);

    //可选参数:classId
    Boolean modifyStudentById(Integer id, String name, Integer age, Boolean gender, Integer classId);

    List<StudentTable> getStudent(Integer id, String name, String teacherName, String className);
}
