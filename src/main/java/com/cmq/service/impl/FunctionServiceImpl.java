package com.cmq.service.impl;

import com.cmq.bo.response.app.FunctionTreeResponseBO;
import com.cmq.mapper.FunctionMapper;
import com.cmq.po.FunctionPO;
import com.cmq.service.FunctionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

    @Resource
    private FunctionMapper functionMapper;

    @Override
    public FunctionTreeResponseBO findAllFunctionsAsTree() {
        List<FunctionPO> allPOs = functionMapper.findAll();

        FunctionTreeResponseBO rootBO = new FunctionTreeResponseBO();

        FunctionPO rootPO = allPOs.stream().filter(function -> function.getParentId() == -1).findFirst().get();

        BeanUtils.copyProperties(rootPO, rootBO);

        List<FunctionTreeResponseBO> curFatherBOs = Arrays.asList(rootBO);

        List<Integer> fatherIds = Arrays.asList(rootBO.getId());

        List<FunctionPO> curChildrenPOs = allPOs.stream().filter(function -> fatherIds.contains(function.getParentId())).collect(Collectors.toList());

        while (!CollectionUtils.isEmpty(curChildrenPOs)){

            List<FunctionTreeResponseBO> nextFatherBOs = new ArrayList<>();

            for(FunctionPO childrenPO : curChildrenPOs){
                for(FunctionTreeResponseBO fatherBO : curFatherBOs){
                    if(fatherBO.getId().equals(childrenPO.getParentId())){
                        FunctionTreeResponseBO childrenBO = new FunctionTreeResponseBO();

                        BeanUtils.copyProperties(childrenPO, childrenBO);

                        fatherBO.getChildren().add(childrenBO);
                        nextFatherBOs.add(childrenBO);
                    }
                }
            }

            //next loop
            curFatherBOs = nextFatherBOs;

            List<Integer> curFatherIds = curFatherBOs.stream().map(FunctionTreeResponseBO::getId).collect(Collectors.toList());

            curChildrenPOs = allPOs.stream().filter(function -> curFatherIds.contains(function.getParentId())).collect(Collectors.toList());
        }
        return rootBO;
    }
}
