package com.cmit.exceltosql.controller;

import com.cmit.exceltosql.service.ExcelTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 16:50 2018/7/18
 */
@RestController
public class ExcelTransferController {
    @Autowired
    private ExcelTransferService excelTransferService;
    @PostConstruct
    public  void getRptDataBcMmAp(){
        excelTransferService.addRptDataBcMmAp();
    }

}
