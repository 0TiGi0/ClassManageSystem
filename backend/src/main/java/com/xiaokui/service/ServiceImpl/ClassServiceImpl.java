package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;
import com.xiaokui.mapper.ClassMapper;
import com.xiaokui.result.R;
import com.xiaokui.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {

    public final ClassMapper classMapper;

    public ClassServiceImpl(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public R<List<ClassTable>> getPage(Integer page, Integer size) {
        return new R<List<ClassTable>>(0, "", classMapper.pageLimit((page - 1) * size, size));
    }

    @Override
    public R<Object> addClass(String name, Integer teacherId) {

        try {
            classMapper.createClass(name, teacherId);
        } catch (Exception e) {
            log.warn("添加班级失败 name={} ,teacherId={}", name, teacherId);
            return new R<Object>(1, "添加班级失败", null);
        }
        log.info("添加班级成功 name={} ,teacherId={}", name, teacherId);
        return new R<Object>(0, "添加班级成功", null);
    }

    @Override
    public R<Object> removeClassById(Integer id) {
        Boolean ret = classMapper.delClass(id);
        if (ret == true) {
            log.info("班级删除成功 id={}", id);
            return new R<Object>(0, "班级删除成功", null);
        } else {
            log.info("班级删除失败 id={}", id);
            return new R<Object>(1, "班级删除失败", null);
        }
    }

    @Override
    public R<Object> modifyClassById(String name, Integer id, Integer teacherId) {
        Boolean ret = classMapper.updateClass(name, id, teacherId);
        if (ret == true) {
            log.info("班级修改成功 name={} ,id={} ,teacherId={}", name, id, teacherId);
            return new R<Object>(0, "班级修改成功", null);
        } else {
            log.warn("班级修改失败 name={} ,id={} ,teacherId={}", name, id, teacherId);
            return new R<Object>(1, "班级修改失败", null);
        }
    }

    @Override
    public R<List<ClassTable>> getClass(Integer id, String name) {
        List<ClassTable> ret = classMapper.queryClass(id, name);
        if (!ret.isEmpty()) {
            log.info("班级查询成功 ret={}", ret);
            return new R<List<ClassTable>>(0, "班级查询成功", ret);
        } else {
            log.warn("班级查询失败 id={},name={}", id, name);
            return new R<List<ClassTable>>(1, "班级查询失败", ret);
        }
    }

    @Override
    public R<List<StudentOnlyIdName>> getAllStudent(Integer id) {
        return new R<List<StudentOnlyIdName>>(0, "", classMapper.getMyAllStudent(id));
    }

    @Override
    public R<Integer> getClassSum() {
        return new R<Integer>(0, "", classMapper.getClassSum());
    }
}
