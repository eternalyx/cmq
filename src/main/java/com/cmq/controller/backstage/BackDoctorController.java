package com.cmq.controller.backstage;

import com.cmq.bo.request.DoctorConfigurationRequestBO;
import com.cmq.bo.request.DoctorHandleRequestBO;
import com.cmq.bo.request.DoctorPageRequestBO;
import com.cmq.bo.response.DoctorConfigurationResponseBO;
import com.cmq.bo.response.DoctorPageResponseBO;
import com.cmq.bo.response.DoctorResponseBO;
import com.cmq.common.BaseResult;
import com.cmq.common.DoctorUsageStateEnum;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.po.DoctorFunctionPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.DistrictService;
import com.cmq.service.DoctorDistrictService;
import com.cmq.service.DoctorFunctionService;
import com.cmq.service.DoctorService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class BackDoctorController {

    @Resource
    private DoctorService doctorService;

    @Resource
    private DistrictService districtService;

    @Resource
    private DoctorDistrictService doctorDistrictService;

    @Resource
    private DoctorFunctionService doctorFunctionService;

    @ResponseBody
    @RequestMapping(value = "/list-by-paging", method = RequestMethod.POST)
    public BaseResult findByPaging(@RequestBody DoctorPageRequestBO params){
        BaseResult result = new BaseResult();
        if(StringUtils.isEmpty(params.getName())){
            params.setName(null);
        }

        if(StringUtils.isEmpty(params.getIdCardNumber())){
            params.setIdCardNumber(null);
        }

        List<DoctorPO> doctorPOs = doctorService.findByPaging(params);

        List<DoctorResponseBO> doctorResponseBOs = new ArrayList<>();
        for(DoctorPO doctorPO : doctorPOs){
            DoctorResponseBO doctorResponseBO = new DoctorResponseBO();

            BeanUtils.copyProperties(doctorPO, doctorResponseBO);
            doctorResponseBO.setUsageState(DoctorUsageStateEnum.valueOf(doctorPO.getUsageState()).getDesc());

            doctorResponseBOs.add(doctorResponseBO);
        }

        int total = doctorService.count(params);

        DoctorPageResponseBO doctorPageResponseBO = new DoctorPageResponseBO();
        doctorPageResponseBO.setDoctors(doctorResponseBOs);
        doctorPageResponseBO.setPageNo(params.getPageNo());
        doctorPageResponseBO.setPageSize(params.getPageSize());
        doctorPageResponseBO.setTotal(total);

        return result.data("paging", doctorPageResponseBO);
    }

    @ResponseBody
    @RequestMapping(value = "/stop-using-batch", method = RequestMethod.POST)
    public BaseResult stopUsingBatch(@RequestBody DoctorHandleRequestBO params){
        params.setUsageState(DoctorUsageStateEnum.NOT_ENABLED.getDatabaseCode());

        doctorService.changeUsageState(params);

        return new BaseResult().success("批量停用成功");
    }

    @ResponseBody
    @RequestMapping(value = "/change-usage-state", method = RequestMethod.POST)
    public BaseResult changeUsageState(@RequestBody DoctorHandleRequestBO params){
        if(DoctorUsageStateEnum.ENABLED.getDesc().equals(params.getUsageState())){
            params.setUsageState(DoctorUsageStateEnum.ENABLED.getDatabaseCode());
        }else {
            params.setUsageState(DoctorUsageStateEnum.NOT_ENABLED.getDatabaseCode());
        }

        doctorService.changeUsageState(params);

        return new BaseResult().success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public BaseResult resetPassword(@RequestBody DoctorHandleRequestBO params){
        BaseResult result = new BaseResult();

        int reset = doctorService.resetPassword(params);
        if(reset != 1){
            return result.fail("重置密码失败");
        }

        return result.success("重置密码成功");
    }

    @ResponseBody
    @RequestMapping(value = "/insert-or-update-configuration", method = RequestMethod.POST)
    public BaseResult insertOrUpdateConfiguration(@RequestBody DoctorConfigurationRequestBO params){
        BaseResult result = new BaseResult();

        if("0".equals(params.getSex())){
            params.setSex("男");
        }else {
            params.setSex("女");
        }

        //concat organization
        if(!ArrayUtils.isEmpty(params.getDistrictIds())){
            List<DistrictPO> districtPOs = districtService.find(Arrays.asList(params.getDistrictIds()));
            String organization = districtPOs.stream().sorted(Comparator.comparingInt(d -> d.getDistrictCode().length()))
                    .map(DistrictPO::getName).collect(Collectors.joining(""));

            params.setOrganization(organization);
        }

        boolean isUpdate = params.getId() != null;

        if(isUpdate){
            DoctorPO doctorPO = doctorService.select(params.getId());
            BeanUtils.copyProperties(params, doctorPO);

            doctorService.update(doctorPO);
        }else {
            doctorService.insert(params);
        }

        doctorDistrictService.insertOrUpdateByDoctorId(params, isUpdate);

        doctorFunctionService.insertOrUpdateByDoctorId(params, isUpdate);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/configuration-detail", method = RequestMethod.GET)
    public BaseResult getDoctorConfigurationDetail(int doctorId){
        BaseResult result = new BaseResult();

        DoctorConfigurationResponseBO responseBO = new DoctorConfigurationResponseBO();

        DoctorPO doctorPO = doctorService.select(doctorId);
        BeanUtils.copyProperties(doctorPO, responseBO);

        String sex = "男".equals(doctorPO.getSex()) ? 0 + "" : 1 + "";
        responseBO.setSex(sex);

        List<DoctorDistrictPO> doctorDistrictPOs = doctorDistrictService.findByDoctorId(doctorId);
        if(!CollectionUtils.isEmpty(doctorDistrictPOs)){
            responseBO.setIsResponsible(doctorDistrictPOs.get(0).getIsResponsible());

            List<Integer> districtIds = doctorDistrictPOs.stream().map(DoctorDistrictPO::getDistrictId).collect(Collectors.toList());
            responseBO.setDistrictIds(districtIds);
        }

        List<DoctorFunctionPO> doctorFunctionPOs = doctorFunctionService.findByDoctorId(doctorId);
        if(!CollectionUtils.isEmpty(doctorFunctionPOs)){
            List<Integer> functionIds = doctorFunctionPOs.stream().map(DoctorFunctionPO::getFunctionId).collect(Collectors.toList());
            responseBO.setFunctionIds(functionIds);
        }

        return result.data("detail", responseBO);
    }
}

