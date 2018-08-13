package com.cmit.hlwjs.datatoexcel.controller;

import com.cmit.hlwjs.datatoexcel.service.AccountDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 21:19 2018/7/5
 */
@RestController
public class AccountDisplayController {
    @Autowired
    private AccountDisplayService accountDisplayService;
    @RequestMapping(value = "/getCompanyServiceFeeInfo",method = RequestMethod.GET)
    public String getCompanyServiceFeeInfo(){
        accountDisplayService.getCompanyServiceFee();
        System.out.println("成功导出企业服务费报表");
        return ("export excel success");
    }
    //test
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayhello(){
        return ("hello");
    }
}
