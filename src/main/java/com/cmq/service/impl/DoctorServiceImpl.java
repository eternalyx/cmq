package com.cmq.service.impl;

import com.cmq.bo.request.app.DoctorConfigurationRequestBO;
import com.cmq.bo.request.app.DoctorHandleRequestBO;
import com.cmq.bo.request.app.DoctorPageRequestBO;
import com.cmq.common.CmqSystem;
import com.cmq.common.DoctorUsageStateEnum;
import com.cmq.mapper.DoctorMapper;
import com.cmq.po.DoctorPO;
import com.cmq.service.DoctorService;
import com.cmq.utils.PasswordUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;

    private static final String DEFAULT_PASSWORD = "123456";

    private static final String DEFAULT_AVATAR_URI = "http://image.eeesys.com/small/2019/20190318/974979cf_32c6_4a4a_bb7a_044bf2e8f46f.jpg";

    @Override
    public DoctorPO select(int id) {
        return doctorMapper.select(id);
    }

    @Override
    public DoctorPO selectByName(String name) {
        return doctorMapper.selectByName(name);
    }

    @Override
    public DoctorPO selectByMobile(String mobile) {
        return doctorMapper.selectByMobile(mobile);
    }

    @Override
    public List<DoctorPO> find(List<Integer> ids) {
        return doctorMapper.find(ids);
    }

    @Override
    public List<DoctorPO> findByPaging(DoctorPageRequestBO params) {
        return doctorMapper.findByPaging(params);
    }

    @Override
    public int count(DoctorPageRequestBO params) {
        return doctorMapper.count(params);
    }

    @Override
    public int insert(DoctorConfigurationRequestBO params) {
        DoctorPO doctorPO = new DoctorPO();

        BeanUtils.copyProperties(params, doctorPO);

        doctorPO.setPassword(PasswordUtils.EncoderByMD5(DEFAULT_PASSWORD));
        doctorPO.setAvatar(DEFAULT_AVATAR_URI);
        doctorPO.setUsageState(DoctorUsageStateEnum.ENABLED.getDatabaseCode());

        DoctorPO loginUser = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());
        doctorPO.insert(loginUser.getId(), loginUser.getName());

        List<DoctorPO> doctorPOs = Arrays.asList(doctorPO);

        doctorMapper.insertBatch(doctorPOs);

        params.setId(doctorPO.getId());

        return 1;
    }

    @Override
    public int update(DoctorPO doctorPO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());
            doctorPO.update(currentLoggedInDoctor.getId(), currentLoggedInDoctor.getName());

            return doctorMapper.update(doctorPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int changePassword(DoctorHandleRequestBO params) {
        try{
            DoctorPO currentLoggedInDoctor = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());

            params.setLastUpdateId(currentLoggedInDoctor.getId());
            params.setLastUpdateName(currentLoggedInDoctor.getName());

            return doctorMapper.changePassword(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int resetPassword(DoctorHandleRequestBO params) {
        try{
            DoctorPO currentLoggedInDoctor = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());

            params.setLastUpdateId(currentLoggedInDoctor.getId());
            params.setLastUpdateName(currentLoggedInDoctor.getName());

            params.setPassword(PasswordUtils.EncoderByMD5(DEFAULT_PASSWORD));

            return doctorMapper.changePassword(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int changeUsageState(DoctorHandleRequestBO params) {
        try{
            DoctorPO currentLoggedInDoctor = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());

            params.setLastUpdateId(currentLoggedInDoctor.getId());
            params.setLastUpdateName(currentLoggedInDoctor.getName());

            if(ArrayUtils.isEmpty(params.getDoctorIds())){
                params.setDoctorIds(new Integer[]{params.getDoctorId()});
            }

            return doctorMapper.changeUsageState(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
