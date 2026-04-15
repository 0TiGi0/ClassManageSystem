package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.ClassTable;
import com.xiaokui.dto.StudentOnlyIdName;
import com.xiaokui.mapper.ClassMapper;
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
    public List<ClassTable> getPage(Integer page, Integer size) {
        return classMapper.pageLimit((page - 1) * size, size);
    }

    @Override
    public Boolean addClass(String name, Integer teacherId) {

        try {
            classMapper.createClass(name, teacherId);
        } catch (Exception e) {
            log.warn("添加班级失败 name={} ,teacherId={}", name, teacherId);
            return false;
        }
        log.info("添加班级成功 name={} ,teacherId={}", name, teacherId);
        return true;
    }

    @Override
    public Boolean removeClassById(Integer id) {
        Boolean ret = classMapper.delClass(id);
        if (ret == true) log.info("班级删除成功 id={}", id);
        else log.info("班级删除失败 id={}", id);
        return ret;
    }

    @Override
    public Boolean modifyClassById(String name, Integer id, Integer teacherId) {
        Boolean ret = classMapper.updateClass(name, id, teacherId);
        if (ret == true) log.info("班级修改成功 name={} ,id={} ,teacherId={}", name, id, teacherId);
        else log.warn("班级修改失败 name={} ,id={} ,teacherId={}", name, id, teacherId);
        return ret;
    }

    @Override
    public List<ClassTable> getClass(Integer id, String name) {
        List<ClassTable> ret = classMapper.queryClass(id, name);
        if (!ret.isEmpty()) log.info("班级查询成功 ret={}", ret);
        else log.warn("班级查询失败 id={},name={}", id, name);
        return ret;
    }

    @Override
    public List<StudentOnlyIdName> getAllStudent(Integer id) {
        return classMapper.getMyAllStudent(id);

    }

    @Override
    public Integer getClassSum() {
        return classMapper.getClassSum();
    }
}
