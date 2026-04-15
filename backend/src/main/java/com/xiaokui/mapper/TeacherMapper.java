package com.xiaokui.mapper;

import com.xiaokui.dto.TeacherTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<TeacherTable> pageLimit(Integer start, Integer size);

    Integer createTeacher(String name, Integer age, Boolean gender, Integer classId);

    Boolean delTeacher(Integer id);

    Boolean updateTeacher(Integer id, String name, Integer age, Boolean gender, Integer classId);

    List<TeacherTable> queryTeacher(Integer id, String name, String className);
}
