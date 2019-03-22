package com.cmq.controller.backstage;

import com.cmq.bo.request.DistrictRequestBO;
import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.common.BaseResult;
import com.cmq.po.DistrictPO;
import com.cmq.service.DistrictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/district")
public class BackDistrictController {

    @Resource
    private DistrictService districtService;

    @ResponseBody
    @RequestMapping(value = "/list-as-tree", method = RequestMethod.GET)
    public BaseResult findAllDistrictAsTree(){
        List<DistrictTreeBO> tree = districtService.findAllDistrictAsTree();
        return new BaseResult().success().data("tree", tree);
    }

    @ResponseBody
    @RequestMapping(value = "insert-or-update", method = RequestMethod.POST)
    public BaseResult insertOrUpdate(@RequestBody DistrictRequestBO districtRequestBO){
        BaseResult result = new BaseResult();

        DistrictPO districtPO = new DistrictPO();
        BeanUtils.copyProperties(districtRequestBO, districtPO);

        DistrictPO exist = districtService.selectByCode(districtRequestBO.getDistrictCode());

        //insert
        if(districtRequestBO.getId() == null){

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

}
