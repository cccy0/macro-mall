package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.model.UmsMemberLevel;
import com.github.cccy0.mall.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级管理Controller
 * @author cy
 */
@RestController
@Api(tags = "会员等级管理")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {
    private UmsMemberLevelService memberLevelService;

    @Autowired
    public void setMemberLevelService(UmsMemberLevelService memberLevelService) {
        this.memberLevelService = memberLevelService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }
}
