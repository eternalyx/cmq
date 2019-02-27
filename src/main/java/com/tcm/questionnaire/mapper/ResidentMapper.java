package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.ResidentPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResidentMapper {

    int insert(ResidentPO resident);

    /**
     * @param districtId
     * @param condition 姓名或身份证号
     * @return
     */
    List<ResidentPO> findByCondition(@Param("districtId") int districtId, @Param("condition") String condition);
}
