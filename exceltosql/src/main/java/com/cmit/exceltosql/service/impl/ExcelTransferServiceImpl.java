package com.cmit.exceltosql.service.impl;

import com.cmit.exceltosql.mapper.RptDataBcMmApMapper;
import com.cmit.exceltosql.model.RptDataBcMmAp;
import com.cmit.exceltosql.service.ExcelTransferService;
import com.cmit.exceltosql.util.ReadExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 16:53 2018/7/18
 */
@Service
public class ExcelTransferServiceImpl implements ExcelTransferService{
    Logger logger = LoggerFactory.getLogger("ExcelTransferServiceImpl");
    @Autowired
    private RptDataBcMmApMapper rptDataBcMmApMapper;
    @Autowired
    private ReadExcel readExcel;

    /**
     * PATH_TO_SAVE_DIR要保存的地址
     */
    @Value("${fileDirPath}")
    public String fileDirPath;

    // @RequestMapping(value = "/feature", method = RequestMethod.GET)
    public List<RptDataBcMmAp> getRptDataBcMmAp() {
        return rptDataBcMmApMapper.selectAll();
    }

    public void addRptDataBcMmAp() {
        RptDataBcMmAp rptDataBcMmAp = new RptDataBcMmAp();
        logger.info("开始读取excel文件并写入数据库");
        Map<Integer, Map<Integer, Object>> map = readExcel.readExcelFile(fileDirPath);

        //获得Excel表格的内容
        for (int i = 1; i < map.size(); i++) {
            Collection<Object> object = map.get(i).values();
            for (int j = 0; j < object.size(); j++) {

                // Returns an array containing all of the elements in this collection
                Object[] object2 = object.toArray();
                rptDataBcMmAp.setApCode(object2[j++].toString());
                rptDataBcMmAp.setMpCode(object2[j++].toString());
                rptDataBcMmAp.setApName(object2[j++].toString());
                rptDataBcMmAp.setBcReason(object2[j++].toString());
                rptDataBcMmAp.setLiClause(object2[j++].toString());
                rptDataBcMmAp.setProScheme(object2[j++].toString());
                rptDataBcMmAp.setCreDeduction(object2[j++].toString());
                rptDataBcMmAp.setBcApid(object2[j++].toString());
                rptDataBcMmAp.setBcApname(object2[j++].toString());
                rptDataBcMmAp.setBusiType(object2[j++].toString());
                rptDataBcMmAp.setStartTyre(object2[j++].toString());
                rptDataBcMmAp.setBcMonth(object2[j++].toString());
                rptDataBcMmAp.setBusiCode(object2[j++].toString());
                rptDataBcMmAp.setBusiName(object2[j++].toString());

                rptDataBcMmApMapper.insert(rptDataBcMmAp);

            }

        }
    }

    // return seasonFeature;

}
