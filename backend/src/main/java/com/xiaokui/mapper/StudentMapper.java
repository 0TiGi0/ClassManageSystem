package com.xiaokui.mapper;

import com.xiaokui.dto.StudentTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentTable> pageLimit(Integer start, Integer size);

    //无法创建抛异常
    Integer createStudent(String name, Integer age, Boolean gender, Integer classId);

    Boolean delStudent(Integer id);

    Boolean updateStudent(Integer id, String name, Integer age, Boolean gender, Integer classId);

    List<StudentTable> queryStudent(Integer id, String name, String teacherName, String className);

    Integer getStudentSum();
}
