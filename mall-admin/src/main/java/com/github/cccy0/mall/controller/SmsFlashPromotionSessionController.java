package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo: 限时购场次管理Controller
 *
 * @author Zhai
 * 2020/11/30 0:34
 */
@RestController
@Api(tags = "限时购场次管理")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @Autowired
    public void setFlashPromotionSessionService(SmsFlashPromotionSessionService flashPromotionSessionService) {
        this.flashPromotionSessionService = flashPromotionSessionService;
    }
}
