package com.xiaokui.service;


import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;

import java.util.List;

public interface ClassService {
    List<ClassTable> getPage(Integer page, Integer size);

    //可选参数：teacherId
    Boolean addClass(String name, Integer teacherId);

    Boolean removeClassById(Integer id);

    //可选参数: teacherId
    Boolean modifyClassById(String name, Integer id, Integer teacherId);

    //可选参数：id，name
    List<ClassTable> getClass(Integer id, String name);

    List<StudentOnlyIdName> getAllStudent(Integer id);

    Integer getClassSum();
}
