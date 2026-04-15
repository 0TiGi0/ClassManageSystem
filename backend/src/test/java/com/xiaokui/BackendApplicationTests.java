package com.xiaokui;

import com.xiaokui.mapper.ClassMapper;
import com.xiaokui.mapper.PublicMapper;
import com.xiaokui.mapper.StudentMapper;
import com.xiaokui.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BackendApplicationTests {

    @Autowired
    public ClassMapper classMapper;

    @Autowired
    public TeacherMapper teacherMapper;

    @Autowired
    public StudentMapper studentMapper;

    @Autowired
    public PublicMapper publicMapper;


    @Test
    void classMapperTest() {
//        List<ClassTable> pageLimitRet = classMapper.pageLimit(0, 5);
//        pageLimitRet.forEach(e -> log.info(e.toString()));

//        Integer createClassRet = classMapper.createClass("魔法学院", 4);
//        log.info("createClassRet = {}", createClassRet);

//        Boolean classMapperRet = classMapper.delClass(100);
//        log.info(classMapperRet.toString());

//        Boolean updateClassRet = classMapper.updateClass("斗罗大陆", 100, 5);
//        log.info("updateClassRet = {}", updateClassRet);
//
//        List<ClassTable> queryClassById = classMapper.queryClass(1, null);
//        List<ClassTable> queryClassByName = classMapper.queryClass(null, "人工智能");
//        log.info("queryClassById = {}", queryClassById);
//        log.info("queryClassByName = {}", queryClassByName);
//
//        List<StudentOnlyIdName> getMyAllStudentRet = classMapper.getMyAllStudent(1);
//        log.info("getMyAllStudentRet = {}", getMyAllStudentRet);
    }

    @Test
    void teacherMapperTest() {

    }

    @Test
    void studentMapperTest() {

    }

    @Test
    void publicMapperTest() {

    }

}
