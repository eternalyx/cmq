package com.cmq.service.impl;

import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.common.CmqSystem;
import com.cmq.mapper.DistrictMapper;
import com.cmq.mapper.DoctorDistrictMapper;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.DistrictService;
import com.cmq.service.DoctorService;
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

    @Resource
    private DoctorService doctorService;

    @Override
    public DistrictPO select(int id) {
        return districtMapper.select(id);
    }

    @Override
    public DistrictPO selectByCode(String districtCode) {
        return districtMapper.selectByCode(districtCode);
    }

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

    @Override
    public List<DistrictPO> findProvinces() {
        return districtMapper.findProvinces();
    }

    @Override
    public List<DistrictPO> findChildrenByParentId(int districtId) {
        DistrictPO districtPO = districtMapper.select(districtId);
        return districtMapper.findChildrenByParentDistrictCode(districtPO.getDistrictCode());
    }

    @Override
    public List<DistrictPO> findChildrenByParentCode(String districtCode) {
        return districtMapper.findChildrenByParentDistrictCode(districtCode);
    }

    @Override
    public int insert(DistrictPO districtPO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
            districtPO.insert(currentLoggedInDoctor.getId(), currentLoggedInDoctor.getName());

            //校验district code是否重复

            return districtMapper.insert(districtPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DistrictPO districtPO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
            districtPO.update(currentLoggedInDoctor.getId(), currentLoggedInDoctor.getName());

            return districtMapper.update(districtPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByCodeWithChildren(String districtCode) {
        try {
            return districtMapper.deleteByCodeWithChildren(districtCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
