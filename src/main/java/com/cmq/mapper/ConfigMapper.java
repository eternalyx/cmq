package com.cmq.mapper;

import com.cmq.po.ConfigPO;

public interface ConfigMapper {

    ConfigPO selectByKey(String key);
}
