package com.xiaokui.mapper;

import com.xiaokui.dto.ClassOnlyIdName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicMapper {
    /*获取班级（对学生）
    获取所有存在教师的班级，用于学生选择班*/
    List<ClassOnlyIdName> getClassHaveTeacher();

    /*获取班级（对教师）
    获取所有不存在教师的班级，用于教师选择班*/
    List<ClassOnlyIdName> getClassNoTeacher();
}
