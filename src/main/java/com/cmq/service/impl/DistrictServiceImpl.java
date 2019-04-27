package com.cmq.service.impl;

import com.cmq.bo.response.DistrictTreeResponseBO;
import com.cmq.common.CmqSystem;
import com.cmq.mapper.DistrictMapper;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.DistrictService;
import com.cmq.service.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<DistrictTreeResponseBO> findAllDistrictAsTree() {
        List<DistrictPO> allPOs = districtMapper.findAll();

        //district共六个级别：国家、省、市、区、镇、村
        DistrictTreeResponseBO tree = new DistrictTreeResponseBO();

        //first
        DistrictPO firstPO = allPOs.stream().filter(district -> district.getDistrictCode().length() == 1).findFirst().get();
        BeanUtils.copyProperties(firstPO, tree);

        //second
        List<DistrictPO> secondPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 3).collect(Collectors.toList());

        List<DistrictTreeResponseBO> secondBOs = tree.getChildren();

        for(DistrictPO secondPO : secondPOs){
            DistrictTreeResponseBO secondBO = new DistrictTreeResponseBO();
            BeanUtils.copyProperties(secondPO, secondBO);
            secondBOs.add(secondBO);
        }

        //third
        List<DistrictPO> thirdPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 5).collect(Collectors.toList());

        List<DistrictTreeResponseBO> thirdBOs = new ArrayList<>();

        for(DistrictPO thirdPO : thirdPOs){
            for(DistrictTreeResponseBO secondBO : secondBOs){
                if(thirdPO.getDistrictCode().startsWith(secondBO.getDistrictCode())){
                    DistrictTreeResponseBO thirdBO = new DistrictTreeResponseBO();
                    BeanUtils.copyProperties(thirdPO, thirdBO);
                    thirdBOs.add(thirdBO);
                    secondBO.getChildren().add(thirdBO);

                    break;
                }
            }
        }

        //fourth
        List<DistrictPO> fourthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 7).collect(Collectors.toList());

        List<DistrictTreeResponseBO> fourthBOs = new ArrayList<>();

        for(DistrictPO fourthPO : fourthPOs){
            for(DistrictTreeResponseBO thirdBO : thirdBOs){
                if(fourthPO.getDistrictCode().startsWith(thirdBO.getDistrictCode())){
                    DistrictTreeResponseBO fourthBO = new DistrictTreeResponseBO();
                    BeanUtils.copyProperties(fourthPO, fourthBO);
                    fourthBOs.add(fourthBO);
                    thirdBO.getChildren().add(fourthBO);

                    break;
                }
            }
        }

        //fifth
        List<DistrictPO> fifthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 9).collect(Collectors.toList());

        List<DistrictTreeResponseBO> fifthBOs = new ArrayList<>();

        for(DistrictPO fifthPO : fifthPOs){
            for(DistrictTreeResponseBO fourthBO : fourthBOs){
                if(fifthPO.getDistrictCode().startsWith(fourthBO.getDistrictCode())){
                    DistrictTreeResponseBO fifthBO = new DistrictTreeResponseBO();
                    BeanUtils.copyProperties(fifthPO, fifthBO);
                    fifthBOs.add(fifthBO);
                    fourthBO.getChildren().add(fifthBO);

                    break;
                }
            }
        }

        //sixth
        List<DistrictPO> sixthPOs = allPOs.stream().filter(district -> district.getDistrictCode().length() == 11).collect(Collectors.toList());

        List<DistrictTreeResponseBO> sixthBOs = new ArrayList<>();

        for(DistrictPO sixthPO : sixthPOs){
            for(DistrictTreeResponseBO fifthBO : fifthBOs){
                if(sixthPO.getDistrictCode().startsWith(fifthBO.getDistrictCode())){
                    DistrictTreeResponseBO sixthBO = new DistrictTreeResponseBO();
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
    public List<DistrictTreeResponseBO> findAllDistrictsAsTree() {
        List<DistrictTreeResponseBO> tree = new ArrayList<>();

        //for next loop
        List<DistrictTreeResponseBO> nextParentDistrictBOs = new ArrayList<>();

        //all district
        List<DistrictPO> allPOs = districtMapper.findAll();

        for(int height = 1; height < 10; height ++){
            int lengthCode = height * 2 - 1;

            List<DistrictPO> curDistrictPOs = allPOs.stream().filter(districtPO -> districtPO.getDistrictCode().length() == lengthCode).collect(Collectors.toList());

            if(CollectionUtils.isEmpty(curDistrictPOs)){
                break;
            }

            List<DistrictTreeResponseBO> curParentDistrictBOs = nextParentDistrictBOs;
            nextParentDistrictBOs = new ArrayList<>();

            for(DistrictPO districtPO : curDistrictPOs){
                DistrictTreeResponseBO districtTreeResponseBO = new DistrictTreeResponseBO();

                BeanUtils.copyProperties(districtPO, districtTreeResponseBO);

                nextParentDistrictBOs.add(districtTreeResponseBO);

                if(height > 1){
                    DistrictTreeResponseBO curParentDistrictBO = curParentDistrictBOs.stream().filter(parentDistrictBO -> districtPO.getDistrictCode().startsWith(parentDistrictBO.getDistrictCode()))
                            .findFirst().get();

                    curParentDistrictBO.getChildren().add(districtTreeResponseBO);
                }else {
                    //height = 1 -> root
                    tree.add(districtTreeResponseBO);
                }
            }
        }

        return tree;
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
    public List<DistrictPO> findChildrenByParentCodes(List<String> districtCodes) {
        return districtMapper.findChildrenByParentCodes(districtCodes);
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
