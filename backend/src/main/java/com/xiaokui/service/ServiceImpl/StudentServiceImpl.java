package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.StudentTable;
import com.xiaokui.mapper.StudentMapper;
import com.xiaokui.result.R;
import com.xiaokui.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    public final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public R<List<StudentTable>> getPage(Integer page, Integer size) {
        return new R<>(0, "", studentMapper.pageLimit((page - 1) * size, size));
    }

    @Override
    public R<Object> addStudent(String name, Integer age, Boolean gender, Integer classId) {
        try {
            studentMapper.createStudent(name, age, gender, classId);
        } catch (Exception e) {
            log.warn("学生添加失败 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
            return new R<>(1, "学生添加失败", null);
        }
        log.info("学生添加成功 name={} ,age={} ,gender={} ,classId={}", name, age, gender, classId);
        return new R<>(0, "学生添加成功", null);
    }

    @Override
    public R<Object> removeStudentById(Integer id) {
        Boolean ret = studentMapper.delStudent(id);
        if (ret == true) {
            log.info("学生删除成功 id={}", id);
            return new R<>(0, "学生删除成功", null);
        } else {
            log.warn("学生删除失败 id={}", id);
            return new R<>(1, "学生删除失败", null);
        }
    }

    @Override
    public R<Object> modifyStudentById(Integer id, String name, Integer age, Boolean gender, Integer classId) {
        Boolean ret = studentMapper.updateStudent(id, name, age, gender, classId);
        if (ret == true) {
            log.info("学生修改成功 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
            return new R<>(0, "学生修改成功", null);
        } else {
            log.warn("学生修改失败 id={} ,name={} ,age={} ,gender={} ,classId={}", id, name, age, gender, classId);
            return new R<>(1, "学生修改失败", null);
        }
    }

    @Override
    public R<List<StudentTable>> getStudent(Integer id, String name, String teacherName, String className) {
        List<StudentTable> ret = studentMapper.queryStudent(id, name, teacherName, className);
        if (!ret.isEmpty()) {
            log.info("学生查询成功 ret={}", ret);
            return new R<>(0, "学生查询成功", ret);
        } else {
            log.info("学生查询失败 id={} ,name={} ,teacherName={} ,className={}", id, name, teacherName, className);
            return new R<>(1, "学生查询失败", ret);
        }
    }

    @Override
    public R<Integer> getStudentSum() {
        return new R<>(0, "", studentMapper.getStudentSum());
    }

}
