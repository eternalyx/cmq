package com.cmq.controller.app;

import com.cmq.bo.request.QuestionnaireRequestBO;
import com.cmq.bo.response.DoneQuestionnaireResidentsResponseBO;
import com.cmq.bo.response.QuestionnaireResponseBO;
import com.cmq.common.BaseResult;
import com.cmq.common.CmqSystem;
import com.cmq.po.DoctorPO;
import com.cmq.po.ResidentPO;
import com.cmq.po.QuestionnairePO;
import com.cmq.service.DoctorService;
import com.cmq.service.QuestionnaireService;
import com.cmq.service.ResidentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

    @Resource
    private DoctorService doctorService;

    @Resource
    private ResidentService residentService;

    @Resource
    private QuestionnaireService questionnaireService;

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public BaseResult selectDetail(int id){
        QuestionnaireResponseBO bo = new QuestionnaireResponseBO();

        QuestionnairePO questionnairePO = questionnaireService.select(id);

        ResidentPO residentPO = residentService.select(questionnairePO.getResidentId());

        BeanUtils.copyProperties(questionnairePO, bo);

        bo.setResidentName(residentPO.getName());
        bo.setSex(residentPO.getSex());
        bo.setIdCardNumber(residentPO.getIdCardNumber());
        bo.setDoctorId(questionnairePO.getDoctorId());
        bo.setLastDoneQuestionnaireTime(questionnairePO.getCreateTime());

        //new add
        bo.setAvatar(residentPO.getAvatar());
        bo.setBirthday(residentPO.getBirthday());
        bo.setPermanentAddress(residentPO.getPermanentAddress());
        bo.setResidenceAddress(residentPO.getResidenceAddress());

        return new BaseResult().data("questionnaire", bo);
    }

    @ResponseBody
    @RequestMapping(value = "/list-last-every-resident", method = RequestMethod.GET)
    public BaseResult findLastEveryResident(String condition){
        List<DoneQuestionnaireResidentsResponseBO> bos = new ArrayList<>();

        List<ResidentPO> residentPOS = residentService.findByCondition(condition);
        if(CollectionUtils.isEmpty(residentPOS)){
            return new BaseResult();
        }

        Map<Integer, ResidentPO> residentPOSMap = residentPOS.stream().collect(Collectors.toMap(ResidentPO::getId, Function.identity()));
        List<Integer> residentIds = residentPOS.stream().map(ResidentPO::getId).collect(Collectors.toList());

        List<QuestionnairePO> idObjects = questionnaireService.findLastEveryResident(residentIds);
        if(CollectionUtils.isEmpty(idObjects)){
            return new BaseResult();
        }

        List<Integer> ids = idObjects.stream().map(QuestionnairePO::getId).collect(Collectors.toList());
        List<QuestionnairePO> questionnairePOS = questionnaireService.find(ids);

        List<Integer> doctorIds = questionnairePOS.stream().map(QuestionnairePO::getDoctorId).collect(Collectors.toList());
        List<DoctorPO> doctorPOS = doctorService.find(doctorIds);
        Map<Integer, DoctorPO> doctorPOSMap = doctorPOS.stream().collect(Collectors.toMap(DoctorPO::getId, Function.identity()));

        for(QuestionnairePO questionnairePO : questionnairePOS){
            DoneQuestionnaireResidentsResponseBO bo = new DoneQuestionnaireResidentsResponseBO();

            ResidentPO residentPO = residentPOSMap.get(questionnairePO.getResidentId());
            DoctorPO doctorPO = doctorPOSMap.get(questionnairePO.getDoctorId());

            bo.setResidentId(questionnairePO.getResidentId());
            bo.setResidentName(residentPO.getName());
            bo.setSex(residentPO.getSex());
            bo.setIdCardNumber(residentPO.getIdCardNumber());
            bo.setDoctorId(questionnairePO.getDoctorId());
            bo.setDoctorName(doctorPO.getName());
            bo.setLastDoneQuestionnaireTime(questionnairePO.getCreateTime());

            bos.add(bo);
        }
        return new BaseResult().data("residents", bos);
    }

    @ResponseBody
    @RequestMapping(value = "/list-by-resident", method = RequestMethod.GET)
    public BaseResult findQuestionnairesByResident(int residentId){
        List<QuestionnaireResponseBO> bos = new ArrayList<>();

        List<QuestionnairePO> questionnairePOS = questionnaireService.findByResidentId(residentId);
        if(CollectionUtils.isEmpty(questionnairePOS)){
            return new BaseResult();
        }

        //doctor information
        List<Integer> doctorIds = questionnairePOS.stream().map(QuestionnairePO::getDoctorId).collect(Collectors.toList());
        List<DoctorPO> doctorPOS = doctorService.find(doctorIds);
        Map<Integer, DoctorPO> doctorPOSMap = doctorPOS.stream().collect(Collectors.toMap(DoctorPO::getId, Function.identity()));

        //resident information
        ResidentPO residentPO = residentService.select(residentId);

        for(QuestionnairePO questionnairePO : questionnairePOS){
            QuestionnaireResponseBO bo = new QuestionnaireResponseBO();
            DoctorPO doctorPO = doctorPOSMap.get(questionnairePO.getDoctorId());

            BeanUtils.copyProperties(questionnairePO, bo);

            bo.setResidentId(questionnairePO.getResidentId());
            bo.setResidentName(residentPO.getName());
            bo.setSex(residentPO.getSex());
            bo.setIdCardNumber(residentPO.getIdCardNumber());
            bo.setDoctorId(questionnairePO.getDoctorId());
            bo.setDoctorName(doctorPO.getName());
            bo.setLastDoneQuestionnaireTime(questionnairePO.getCreateTime());

            bos.add(bo);
        }
        return new BaseResult().data("questionnaires", bos);
    }

    @ResponseBody
    @RequestMapping(value = "/insert-or-update", method = RequestMethod.POST)
    public BaseResult insertOrUpdate(@RequestBody QuestionnaireRequestBO questionnaireRequestBO){
        BaseResult result = new BaseResult();

        QuestionnairePO questionnairePO = new QuestionnairePO();
        BeanUtils.copyProperties(questionnaireRequestBO, questionnairePO);

        if(questionnairePO.getId() == null){
            questionnairePO.setDoctorId(CmqSystem.getCurrentLoggedInUser().getId());

            int insert = questionnaireService.insert(questionnairePO);

            if(insert != 1){
                return result.fail("新增辨识结果失败");
            }
            return result.success("新增辨识结果成功").data("id", questionnairePO.getId());
        }else{
            //校验是否为本人（医生）的辨识结果
            if(!CmqSystem.getCurrentLoggedInUser().getId().equals(questionnairePO.getDoctorId())){
                return result.fail("无权限修改");
            }

            int update = questionnaireService.update(questionnairePO);

            if(update != 1){
                return result.fail("修改辨识结果失败");
            }
            return result.success("修改辨识结果成功").data("id",questionnairePO.getId());
        }
    }

}
