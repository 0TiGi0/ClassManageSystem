package com.xiaokui.mapper;

import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<ClassTable> pageLimit(Integer start, Integer size);

    //创建冲突抛异常
    Integer createClass(String name, Integer teacherId);

    Boolean delClass(Integer id);

    Boolean updateClass(String name, Integer id, Integer teacherId);

    List<ClassTable> queryClass(Integer id, String name);

    List<StudentOnlyIdName> getMyAllStudent(Integer id);

    Integer getClassSum();
}
