package com.cmq.service.impl;

import com.cmq.mapper.GuidanceMapper;
import com.cmq.po.GuidancePO;
import com.cmq.service.GuidanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("guidanceService")
public class GuidanceServiceImpl implements GuidanceService {

    @Resource
    private GuidanceMapper guidanceMapper;

    @Override
    public List<GuidancePO> findAll() {
        return guidanceMapper.findAll();
    }
}
