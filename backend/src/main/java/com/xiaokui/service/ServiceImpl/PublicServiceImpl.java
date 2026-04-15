package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.ClassOnlyIdName;
import com.xiaokui.mapper.PublicMapper;
import com.xiaokui.service.PublicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicServiceImpl implements PublicService {

    public final PublicMapper publicMapper;

    public PublicServiceImpl(PublicMapper publicMapper) {
        this.publicMapper = publicMapper;
    }

    @Override
    public List<ClassOnlyIdName> getClassHaveTeacher() {
        return publicMapper.getClassHaveTeacher();
    }

    @Override
    public List<ClassOnlyIdName> getClassNoTeacher() {
        return publicMapper.getClassNoTeacher();
    }
}
