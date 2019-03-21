package com.cmq.service.impl;

import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.mapper.DistrictMapper;
import com.cmq.mapper.DoctorDistrictMapper;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.service.DistrictService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<DistrictPO> find(List<Integer> ids) {
        return districtMapper.find(ids);
    }

    @Override
    public List<DistrictTreeBO> findAllDistrictAsTree() {
        List<DistrictPO> allPOs = districtMapper.findAll();

        //district共六个级别：国家、省、市、区、镇、村
        DistrictTreeBO tree = new DistrictTreeBO();

        //first
        DistrictPO firstPO = allPOs.stream().filter(district -> district.getDistrictCode().length() == 1).findFirst().get();
        BeanUtils.copyProperties(firstPO, tree);

        //second
        List<DistrictPO> secondPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 3).collect(Collectors.toList());

        List<DistrictTreeBO> secondBOs = tree.getChildren();

        for(DistrictPO secondPO : secondPOs){
            DistrictTreeBO secondBO = new DistrictTreeBO();
            BeanUtils.copyProperties(secondPO, secondBO);
            secondBOs.add(secondBO);
        }

        //third
        List<DistrictPO> thirdPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 5).collect(Collectors.toList());

        List<DistrictTreeBO> thirdBOs = new ArrayList<>();

        for(DistrictPO thirdPO : thirdPOs){
            for(DistrictTreeBO secondBO : secondBOs){
                if(thirdPO.getDistrictCode().startsWith(secondBO.getDistrictCode())){
                    DistrictTreeBO thirdBO = new DistrictTreeBO();
                    BeanUtils.copyProperties(thirdPO, thirdBO);
                    thirdBOs.add(thirdBO);
                    secondBO.getChildren().add(thirdBO);

                    break;
                }
            }
        }

        //fourth
        List<DistrictPO> fourthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 7).collect(Collectors.toList());

        List<DistrictTreeBO> fourthBOs = new ArrayList<>();

        for(DistrictPO fourthPO : fourthPOs){
            for(DistrictTreeBO thirdBO : thirdBOs){
                if(fourthPO.getDistrictCode().startsWith(thirdBO.getDistrictCode())){
                    DistrictTreeBO fourthBO = new DistrictTreeBO();
                    BeanUtils.copyProperties(fourthPO, fourthBO);
                    fourthBOs.add(fourthBO);
                    thirdBO.getChildren().add(fourthBO);

                    break;
                }
            }
        }

        //fifth
        List<DistrictPO> fifthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 9).collect(Collectors.toList());

        List<DistrictTreeBO> fifthBOs = new ArrayList<>();

        for(DistrictPO fifthPO : fifthPOs){
            for(DistrictTreeBO fourthBO : fourthBOs){
                if(fifthPO.getDistrictCode().startsWith(fourthBO.getDistrictCode())){
                    DistrictTreeBO fifthBO = new DistrictTreeBO();
                    BeanUtils.copyProperties(fifthPO, fifthBO);
                    fifthBOs.add(fifthBO);
                    fourthBO.getChildren().add(fifthBO);

                    break;
                }
            }
        }

        //sixth
        List<DistrictPO> sixthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 11).collect(Collectors.toList());

        List<DistrictTreeBO> sixthBOs = new ArrayList<>();

        for(DistrictPO sixthPO : sixthPOs){
            for(DistrictTreeBO fifthBO : fifthBOs){
                if(sixthPO.getDistrictCode().startsWith(fifthBO.getDistrictCode())){
                    DistrictTreeBO sixthBO = new DistrictTreeBO();
                    BeanUtils.copyProperties(sixthPO, sixthBO);
                    sixthBOs.add(sixthBO);
                    fifthBO.getChildren().add(sixthBO);

                    break;
                }
            }
        }

        return Collections.singletonList(tree);
    }

}
