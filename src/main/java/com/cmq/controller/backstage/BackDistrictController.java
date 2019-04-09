package com.cmq.controller.backstage;

import com.cmq.bo.request.DistrictRequestBO;
import com.cmq.bo.response.DistrictSelectorResponseBO;
import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.common.BaseResult;
import com.cmq.po.DistrictPO;
import com.cmq.service.DistrictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/district")
public class BackDistrictController {

    @Resource
    private DistrictService districtService;

    @ResponseBody
    @RequestMapping(value = "/list-as-tree", method = RequestMethod.GET)
    public BaseResult findAllDistrictAsTree(){
        List<DistrictTreeBO> tree = districtService.findAllDistrictsAsTree();
        return new BaseResult().success().data("districtTree", tree);
    }

    @ResponseBody
    @RequestMapping(value = "list-for-selector", method = RequestMethod.GET)
    public BaseResult findDistricts4Selector(Integer districtId){
        BaseResult result = new BaseResult();

        List<DistrictSelectorResponseBO> districtBOs = new ArrayList<>();

        List<DistrictPO> districtPOs;

        if(districtId == null){
            districtPOs = districtService.findProvinces();
        }else {
            districtPOs = districtService.findChildrenByParentId(districtId);
        }

        if(!CollectionUtils.isEmpty(districtPOs)){
            for(DistrictPO districtPO : districtPOs){
                DistrictSelectorResponseBO districtBO = new DistrictSelectorResponseBO();
                BeanUtils.copyProperties(districtPO, districtBO);

                districtBOs.add(districtBO);
            }
        }

        return result.data("districts", districtBOs);
    }

    @ResponseBody
    @RequestMapping(value = "insert-or-update", method = RequestMethod.POST)
    public BaseResult insertOrUpdate(@RequestBody DistrictRequestBO districtRequestBO){
        BaseResult result = new BaseResult();

        if(!checkDistrictCode(districtRequestBO.getDistrictCode())){
            return result.fail("请输入3、5、7、9、11位的组织编号");
        }

        DistrictPO districtPO = new DistrictPO();
        BeanUtils.copyProperties(districtRequestBO, districtPO);

        DistrictPO exist = districtService.selectByCode(districtRequestBO.getDistrictCode());

        //insert
        if(districtRequestBO.getId() == null){

            if(districtRequestBO.getIsChildren() == 0){
                if(districtRequestBO.getBeforeDistrictCode().length() != districtRequestBO.getDistrictCode().length()){
                    return result.fail("同级组织编号的长度应为：" + districtRequestBO.getBeforeDistrictCode().length());
                }
            }

            if(districtRequestBO.getIsChildren() == 1){
                if((districtRequestBO.getBeforeDistrictCode().length() + 2) != districtRequestBO.getDistrictCode().length()){
                    return result.fail("下级组织编号的长度应为：" + (districtRequestBO.getBeforeDistrictCode().length() + 2));
                }
            }

            if(!ObjectUtils.isEmpty(exist)){
                return result.fail("组织编号已存在");
            }

            int insert = districtService.insert(districtPO);
            if(insert != 1){
                return result.fail("新增组织失败");
            }
            return result.success("新增组织成功");

        }else{

            if(!ObjectUtils.isEmpty(exist) && !exist.getId().equals(districtRequestBO.getId())){
                return result.fail("组织编号已存在");
            }

            int update = districtService.update(districtPO);
            if(update != 1){
                return result.fail("修改组织失败");
            }
            return result.success("修改组织成功");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(@RequestBody DistrictRequestBO districtRequestBO){
        DistrictPO districtPO = districtService.select(districtRequestBO.getId());

        if(!ObjectUtils.isEmpty(districtPO)){
            districtService.deleteByCodeWithChildren(districtPO.getDistrictCode());
        }

        return new BaseResult().success("删除成功");
    }

    private boolean checkDistrictCode(String districtCode){
        if(StringUtils.isEmpty(districtCode)){
            return false;
        }

        if(districtCode.length() > 11 || districtCode.length() < 2){
            return false;
        }

        if(districtCode.length() % 2 == 0){
            return false;
        }

        return true;
    }

}
