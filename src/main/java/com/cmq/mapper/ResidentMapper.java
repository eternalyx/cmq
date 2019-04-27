package com.cmq.mapper;

import com.cmq.po.ResidentPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResidentMapper {

    ResidentPO select(int id);

    /**
     * @param condition 姓名or身份证号
     * @return
     */
    List<ResidentPO> findByCondition(@Param("doctorIds") List<Integer> doctorIds, @Param("condition") String condition);

    int insert(ResidentPO residentPO);

    int update(ResidentPO residentPO);
}
