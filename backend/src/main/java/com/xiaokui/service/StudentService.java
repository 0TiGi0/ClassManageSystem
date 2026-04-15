package com.xiaokui.service;

import com.xiaokui.dto.StudentTable;
import com.xiaokui.result.R;

import java.util.List;

public interface StudentService {
    R<List<StudentTable>> getPage(Integer page, Integer size);

    //可选参数:classId
    R<Object> addStudent(String name, Integer age, Boolean gender, Integer classId);

    R<Object> removeStudentById(Integer id);

    //可选参数:classId
    R<Object> modifyStudentById(Integer id, String name, Integer age, Boolean gender, Integer classId);

    R<List<StudentTable>> getStudent(Integer id, String name, String teacherName, String className);

    R<Integer> getStudentSum();
}
