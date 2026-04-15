package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.TeacherTable;
import com.xiaokui.mapper.TeacherMapper;
import com.xiaokui.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    public final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<TeacherTable> getPage(Integer page, Integer size) {
        return teacherMapper.pageLimit((page - 1) * size, size);
    }

    @Override
    public boolean addTeacher(String name, Integer age, Boolean gender, Integer classId) {
        try {
            teacherMapper.createTeacher(name, age, gender, classId);
        } catch (Exception e) {
            log.warn("学生添加失败 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
            return false;
        }
        log.info("学生添加成功 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
        return true;
    }

    @Override
    public Boolean removeTeacherById(Integer id) {
        Boolean ret = teacherMapper.delTeacher(id);
        if (ret == true) log.info("学生删除成功 id={}", id);
        else log.warn("学生删除失败 id={}", id);
        return ret;
    }

    @Override
    public Boolean modifyTeacherById(Integer id, String name, Integer age, Boolean gender, Integer classId) {
        Boolean ret = teacherMapper.updateTeacher(id, name, age, gender, classId);
        if (ret == true)
            log.info("学生修改成功 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
        else log.warn("学生修改失败 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
        return ret;
    }

    @Override
    public List<TeacherTable> getTeacher(Integer id, String name, String className) {
        List<TeacherTable> ret = teacherMapper.queryTeacher(id, name, className);
        if (!ret.isEmpty()) log.info("学生查询成功 ret={}", ret);
        else log.info("学生查询失败 id={} ,name={},className={}", id, name, className);
        return ret;
    }
}
