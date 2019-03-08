package com.cmq.service;

import com.cmq.po.ConfigPO;

public interface ConfigService {

    ConfigPO selectByKey(String key);
}
