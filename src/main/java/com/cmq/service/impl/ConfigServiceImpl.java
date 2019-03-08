package com.cmq.service.impl;

import com.cmq.mapper.ConfigMapper;
import com.cmq.po.ConfigPO;
import com.cmq.service.ConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    @Override
    public ConfigPO selectByKey(String key) {
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return configMapper.selectByKey(key);
    }
}
