package com.cmit.hlwjs.datatoexcel.service.impl;

import com.cmit.hlwjs.datatoexcel.mapper.RptDataMmApMapper;
import com.cmit.hlwjs.datatoexcel.model.RptDataMmAp;
import com.cmit.hlwjs.datatoexcel.service.AccountDisplayService;
import com.cmit.hlwjs.datatoexcel.util.ExportExcelUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 16:53 2018/7/5
 */
@Service
public class AccountDisplayServiceImpl implements AccountDisplayService {
    private RptDataMmApMapper rptDataMmApMapper;
    @Override
    public void getCompanyServiceFee() {
        List<RptDataMmAp> rptDataMmApList = rptDataMmApMapper.selectAll();
        if (rptDataMmApList != null) {
            try{
                String[] titles_qyfwf = {"账期","AP代码","AP名称","服务费金额"};
                ExportExcelUtil.exportCompanyServiceFee(rptDataMmApList,titles_qyfwf);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
