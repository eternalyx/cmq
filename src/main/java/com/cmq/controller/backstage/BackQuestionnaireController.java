package com.cmq.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.cmq.bo.request.web.QuestionnaireOperationRequestBO;
import com.cmq.bo.request.web.QuestionnairePageRequestBO;
import com.cmq.bo.response.web.QuestionnaireDetailVO;
import com.cmq.bo.response.web.QuestionnairePageVO;
import com.cmq.bo.response.web.QuestionnaireScoreVO;
import com.cmq.bo.response.web.QuestionnaireVO;
import com.cmq.common.BaseResult;
import com.cmq.common.CmqSystem;
import com.cmq.po.DoctorPO;
import com.cmq.po.QuestionnairePO;
import com.cmq.po.ResidentPO;
import com.cmq.service.DoctorService;
import com.cmq.service.QuestionnaireService;
import com.cmq.service.ResidentService;
import com.cmq.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web-questionnaire")
public class BackQuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private DoctorService doctorService;

    private static final String[] constitutions =
            new String[]{"qixu", "yangxu", "yinxu", "tanshi", "shire", "xueyu", "qiyu", "tebing", "pinghe"};

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @ResponseBody
    @RequestMapping(value = "/list-by-paging", method = RequestMethod.POST)
    public BaseResult page(@RequestBody QuestionnairePageRequestBO params){

        if(!StringUtils.isEmpty(params.getResidentName())){
            List<Integer> ids = residentService.findByCondition(params.getResidentName())
                    .stream().map(ResidentPO::getId).collect(Collectors.toList());

            if(CollectionUtils.isEmpty(ids)){
                params.getResidentIds().add(-1);
            }else{
                params.getResidentIds().addAll(ids);
            }

        }
        if(!StringUtils.isEmpty(params.getIdCardNumber())){
            List<Integer> ids = residentService.findByCondition(params.getIdCardNumber())
                    .stream().map(ResidentPO::getId).collect(Collectors.toList());

            if(CollectionUtils.isEmpty(ids)){
                params.setResidentIds(new ArrayList<>());
                params.getResidentIds().add(-1);
            }else {
                if(CollectionUtils.isEmpty(params.getResidentIds())){
                    params.getResidentIds().addAll(ids);
                }else if(params.getResidentIds().contains(-1)){

                } else{
                    params.getResidentIds().retainAll(ids);
                }
            }
        }

        //creator id
        //List<Integer> doctorIds = doctorDistrictService.findSubordinateDoctorIdsByLoginUser();

        List<QuestionnairePO> questionnairePOs = questionnaireService.page(params);

        BaseResult result = new BaseResult();

        QuestionnairePageVO page = new QuestionnairePageVO();
        page.setPageNo(params.getPageNo());
        page.setPageSize(params.getPageSize());

        List<QuestionnaireVO> questionnaireVOs = new ArrayList<>();

        if(CollectionUtils.isEmpty(questionnairePOs)){
            page.setTotal(0);
            return result.data("page", page);
        }

        for(QuestionnairePO po : questionnairePOs){
            QuestionnaireVO vo = new QuestionnaireVO();
            BeanUtils.copyProperties(po, vo);

            vo.setGuidanceTime(sdf.format(po.getCreateTime().getTime()));
            vo.setResult(parse(po));

            ResidentPO resident = residentService.select(po.getResidentId());
            vo.setResidentName(resident.getName());
            vo.setSex(resident.getSex());
            vo.setIdCardNumber(resident.getIdCardNumber());

            DoctorPO doctor = doctorService.select(po.getDoctorId());
            vo.setDoctorName(doctor.getName());

            questionnaireVOs.add(vo);
        }

        page.setTotal(questionnaireService.count(params));
        page.setQuestionnaires(questionnaireVOs);
        return result.data("page", page);
    }


    @ResponseBody
    @RequestMapping(value = "/delete-some", method = RequestMethod.POST)
    public BaseResult deleteSome(@RequestBody QuestionnaireOperationRequestBO params){
        BaseResult result = new BaseResult();

        if(CollectionUtils.isEmpty(params.getQuestionnaireIds())){
            return result.fail("请选择要删除的辨识记录");
        }

        questionnaireService.deleteSome(params);
        return result.success("删除成功");
    }


    private String parse(QuestionnairePO po){
        StringBuffer constitution = new StringBuffer();

        if(judge(po.getYinxu())){
            constitution.append("阴虚质、");
        }
        if(judge(po.getYangxu())){
            constitution.append("阳虚质、");
        }
        if(judge(po.getTanshi())){
            constitution.append("痰湿质、");
        }
        if(judge(po.getShire())){
            constitution.append("湿热质、");
        }
        if(judge(po.getXueyu())){
            constitution.append("血瘀质、");
        }
        if(judge(po.getQixu())){
            constitution.append("气瘀质、");
        }
        if(judge(po.getTebing())){
            constitution.append("特禀质、");
        }
        if(judge(po.getPinghe())){
            constitution.append("平和质、");
        }

        if(constitution.length() > 0){
            return constitution.substring(0, constitution.length() - 1);
        }
        return "";
    }

    private boolean judge(String result){
        if(!StringUtils.isEmpty(result) && result.contains("是")){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/detail")
    public BaseResult detail(int questionnaireId){
        QuestionnairePO questionnairePO = questionnaireService.select(questionnaireId);
        return new BaseResult().data("detail", analysisPO(questionnairePO));
    }

    private QuestionnaireDetailVO analysisPO(QuestionnairePO questionnairePO){
        QuestionnaireDetailVO vo = new QuestionnaireDetailVO();
        Class<? extends QuestionnaireDetailVO> vClass = vo.getClass();
        Class<? extends QuestionnairePO> pClass = questionnairePO.getClass();

        //options
        String option = questionnairePO.getOptions();
        String[] options = option.substring(1, option.length() - 1).split(",");
        for(int index = 0; index < options.length; index++){
            try {
                Field field = vClass.getDeclaredField("c" + (index + 1));
                field.setAccessible(true);
                field.set(vo, Long.valueOf(options[index]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int index = 0; index < constitutions.length; index++){
            String constitution = constitutions[index];
            try {
                Field pField = pClass.getDeclaredField(constitution);
                pField.setAccessible(true);
                String result = pField.get(questionnairePO).toString();

                QuestionnaireScoreVO scoreVO = JSON.parseObject(result, QuestionnaireScoreVO.class);

                //vo score
                Field vScore = vClass.getDeclaredField(constitution);
                vScore.setAccessible(true);
                vScore.set(vo, scoreVO.getScore());

                Field vJudge = vClass.getDeclaredField(constitution + "J");
                vJudge.setAccessible(true);
                vJudge.set(vo, scoreVO.getResult());

                Field vGuidance = vClass.getDeclaredField(constitution + "Guidance");
                vGuidance.setAccessible(true);
                vGuidance.set(vo, scoreVO.getGuidance());

                Field vOther = vClass.getDeclaredField(constitution + "Other");
                vOther.setAccessible(true);
                vOther.set(vo, scoreVO.getGuidanceOther());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        vo.setDate(questionnairePO.getCreateTime().toString());

        DoctorPO doctorPO = doctorService.select(questionnairePO.getDoctorId());
        vo.setOp(doctorPO.getName());

        ResidentPO resident = residentService.select(questionnairePO.getResidentId());
        vo.setResidentName(resident.getName());
        vo.setAddress(resident.getPermanentAddress());
        vo.setSex(resident.getSex());
        vo.setBirthday(resident.getBirthday());
        vo.setIdCardNumber(resident.getIdCardNumber());
        vo.setAge(CommonUtils.calculateAgeByBirthday(resident.getBirthday()));
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult createOrUpdate(@RequestBody QuestionnaireDetailVO vo){
        QuestionnairePO paramsPO = analysisVO(vo);

        if(vo.getId() == null){
            ResidentPO resident = new ResidentPO();
            resident.setBirthday(vo.getBirthday());
            resident.setIdCardNumber(vo.getIdCardNumber());
            resident.setName(vo.getResidentName());
            resident.setPermanentAddress(vo.getAddress());
            resident.setSex(vo.getSex());
            int residentId = residentService.insert(resident);

            QuestionnairePO insertPO = new QuestionnairePO();
            Class<? extends QuestionnairePO> poClass = insertPO.getClass();

            insertPO.setOptions(paramsPO.getOptions());

            for(int index = 0; index < constitutions.length; index ++){
                String constitution = constitutions[index];
                try {
                    Field poField = poClass.getDeclaredField(constitution);
                    poField.setAccessible(true);
                    String param = poField.get(paramsPO).toString();
                    poField.set(insertPO, param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            insertPO.setDoctorId(CmqSystem.getCurrentLoggedInUser().getId());
            insertPO.setResidentId(resident.getId());

            questionnaireService.insert(insertPO);
        }else {
            QuestionnairePO updatePO = questionnaireService.select(vo.getId());
            Class<? extends QuestionnairePO> poClass = updatePO.getClass();

            updatePO.setOptions(paramsPO.getOptions());

            for(int index = 0; index < constitutions.length; index ++){
                String constitution = constitutions[index];
                try {
                    Field poField = poClass.getDeclaredField(constitution);
                    poField.setAccessible(true);
                    String param = poField.get(paramsPO).toString();
                    poField.set(updatePO, param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            questionnaireService.update(updatePO);
        }
        return new BaseResult().success();
    }

    private QuestionnairePO analysisVO(QuestionnaireDetailVO vo){
        QuestionnairePO po = new QuestionnairePO();
        Class<? extends QuestionnaireDetailVO> vClass = vo.getClass();
        Class<? extends QuestionnairePO> pClass = po.getClass();

        //33 options
        String jsonOptions = "[";
        for(int index = 0; index < 33; index ++){
            try {
                Field vOption = vClass.getDeclaredField("c" + (index + 1));
                vOption.setAccessible(true);
                String option = vOption.get(vo).toString();
                jsonOptions += option;

                if(index < 32){ jsonOptions += ","; }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonOptions += "]";
        po.setOptions(jsonOptions);

        //constitution
        for(int index = 0; index < constitutions.length; index ++){
            String constitution = constitutions[index];
            QuestionnaireScoreVO score = new QuestionnaireScoreVO();

            try{
                Field pScore = vClass.getDeclaredField(constitution);
                pScore.setAccessible(true);
                Integer s = (Integer) pScore.get(vo);
                score.setScore(s);

                Field pResult = vClass.getDeclaredField(constitution + "J");
                pResult.setAccessible(true);
                String result =  pResult.get(vo).toString();
                score.setResult(result);

                Field pGuidance = vClass.getDeclaredField(constitution + "Guidance");
                pGuidance.setAccessible(true);
                String guidance =  pGuidance.get(vo).toString();
                score.setGuidance(guidance);

                Field pOther = vClass.getDeclaredField(constitution + "Other");
                pOther.setAccessible(true);
                String other =  pOther.get(vo).toString();
                score.setGuidanceOther(other);
            }catch (Exception e){
                e.printStackTrace();
            }

            String jScore = JSON.toJSONString(score);

            try {
                Field pConstitution = pClass.getDeclaredField(constitution);
                pConstitution.setAccessible(true);
                pConstitution.set(po, jScore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return po;
    }

}
