package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.TeacherTable;
import com.xiaokui.mapper.TeacherMapper;
import com.xiaokui.result.R;
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
    public R<List<TeacherTable>> getPage(Integer page, Integer size) {
        return new R<>(0, "", teacherMapper.pageLimit((page - 1) * size, size));
    }

    @Override
    public R<Object> addTeacher(String name, Integer age, Boolean gender, Integer classId) {
        try {
            teacherMapper.createTeacher(name, age, gender, classId);
        } catch (Exception e) {
            log.warn("教师添加失败 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
            return new R<>(1, "教师添加失败", null);
        }
        log.info("教师添加成功 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
        return new R<>(0, "教师添加成功", null);
    }

    @Override
    public R<Object> removeTeacherById(Integer id) {
        Boolean ret = teacherMapper.delTeacher(id);
        if (ret == true) {
            log.info("教师删除成功 id={}", id);
            return new R<>(0, "教师删除成功", null);
        } else {
            log.warn("教师删除失败 id={}", id);
            return new R<>(1, "教师删除失败", null);
        }
    }

    @Override
    public R<Object> modifyTeacherById(Integer id, String name, Integer age, Boolean gender, Integer classId) {
        Boolean ret = teacherMapper.updateTeacher(id, name, age, gender, classId);
        if (ret == true) {
            log.info("教师修改成功 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
            return new R<>(0, "教师修改成功", null);
        } else {
            log.warn("教师修改失败 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
            return new R<>(1, "教师修改失败", null);
        }
    }

    @Override
    public R<List<TeacherTable>> getTeacher(Integer id, String name, String className) {
        List<TeacherTable> ret = teacherMapper.queryTeacher(id, name, className);
        if (!ret.isEmpty()) {
            log.info("教师查询成功 ret={}", ret);
            return new R<>(0, "教师查询成功", ret);
        } else {
            log.info("教师查询失败 id={} ,name={} ,className={}", id, name, className);
            return new R<>(1, "教师查询失败", ret);
        }
    }

    @Override
    public R<Integer> getTeacherSum() {
        return new R<>(0, "", teacherMapper.getTeacherSum());
    }
}
