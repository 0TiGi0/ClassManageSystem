package com.xiaokui.service.ServiceImpl;

import com.xiaokui.dto.ClassOnlyIdName;
import com.xiaokui.mapper.PublicMapper;
import com.xiaokui.result.R;
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
    public R<List<ClassOnlyIdName>> getClassHaveTeacher() {
        return new R<>(0, "", publicMapper.getClassHaveTeacher());
    }

    @Override
    public R<List<ClassOnlyIdName>> getClassNoTeacher() {
        return new R<>(0, "", publicMapper.getClassNoTeacher());
    }
}
