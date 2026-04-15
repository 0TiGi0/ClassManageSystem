package com.xiaokui.service;


import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;
import com.xiaokui.result.R;

import java.util.List;

public interface ClassService {
    R<List<ClassTable>> getPage(Integer page, Integer size);

    //可选参数：teacherId
    R<Object> addClass(String name, Integer teacherId);

    R<Object> removeClassById(Integer id);

    //可选参数: teacherId
    R<Object> modifyClassById(String name, Integer id, Integer teacherId);

    //可选参数：id，name
    R<List<ClassTable>> getClass(Integer id, String name);

    R<List<StudentOnlyIdName>> getAllStudent(Integer id);

    R<Integer> getClassSum();
}
