package com.cmq.controller.backstage;

import com.cmq.bo.request.web.QuestionnaireOperationRequestBO;
import com.cmq.bo.request.web.QuestionnairePageRequestBO;
import com.cmq.bo.response.web.QuestionnairePageVO;
import com.cmq.bo.response.web.QuestionnaireVO;
import com.cmq.common.BaseResult;
import com.cmq.po.DoctorPO;
import com.cmq.po.QuestionnairePO;
import com.cmq.po.ResidentPO;
import com.cmq.service.DoctorService;
import com.cmq.service.QuestionnaireService;
import com.cmq.service.ResidentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questionnaire")
public class BackQuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private DoctorService doctorService;

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

            vo.setGuidanceTime(po.getCreateTime().getTime());
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
    @RequestMapping(value = "delete-some", method = RequestMethod.POST)
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

}
