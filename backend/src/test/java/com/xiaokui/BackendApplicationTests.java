package com.xiaokui;

import com.xiaokui.dto.ClassOnlyIdName;
import com.xiaokui.dto.ClassTable;
import com.xiaokui.mapper.ClassMapper;
import com.xiaokui.mapper.PublicMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BackendApplicationTests {

    @Autowired
    public ClassMapper classMapper;

    @Autowired
    public PublicMapper publicMapper;

    @Test
    void classMapperTest() {
        List<ClassTable> classTables = classMapper.pageLimit(0, 5);
        classTables.forEach(e -> log.info(e.toString()));
    }

    @Test
    void publicMapperTest() {
        List<ClassOnlyIdName> classHaveTeacher = publicMapper.getClassHaveTeacher();
        log.info(classHaveTeacher.toString());
    }

}
