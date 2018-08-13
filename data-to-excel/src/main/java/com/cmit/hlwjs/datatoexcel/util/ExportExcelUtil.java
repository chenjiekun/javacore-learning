package com.cmit.hlwjs.datatoexcel.util;


import com.cmit.hlwjs.datatoexcel.model.RptDataMmAp;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
@description excel导出
 **/
public class ExportExcelUtil {


    /***
     * 差异数据导出
     * @return
     */
    public static void exportCompanyServiceFee(List<RptDataMmAp> list, String[] titles) throws Exception {
        //创建一个输出流
        OutputStream outputStream = new FileOutputStream("D:\\企业服务费.xls");
        //创建工作薄
        WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
        //设置sheet名称
        WritableSheet sheet = wwb.createSheet("sheet1", 0);
        //设置单元格的样式
        WritableCellFormat cellFormat = new WritableCellFormat();
        //设置水平居中
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        //设置垂直居中
        cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        //设置自动换行
        cellFormat.setWrap(true);
        Label titleLabel = null;
        titleLabel = new Label(0,0,"企业服务费报表");
        titleLabel.setCellFormat(cellFormat);
        sheet.addCell(titleLabel);
        //第一个参：开始列,第二个参：开始行,第三个参：填充内容

        for (int i = 0; i < titles.length; i++) {
            titleLabel = new Label(i,1, titles[i]);
            titleLabel.setCellFormat(cellFormat);
            sheet.addCell(titleLabel);
        }

        int i = 2;
        Label tl = null;
        for (RptDataMmAp rptDataMmAp : list) {//循环填充数据

            tl = new Label(0, i, rptDataMmAp.getApCodeU());
            tl.setCellFormat(cellFormat);
            sheet.addCell(tl);

            tl = new Label(1, i, rptDataMmAp.getApCode());
            tl.setCellFormat(cellFormat);
            sheet.addCell(tl);

            tl = new Label(2, i, rptDataMmAp.getApName());
            tl.setCellFormat(cellFormat);
            sheet.addCell(tl);

            tl = new Label(3, i, rptDataMmAp.getDataRange());
            tl.setCellFormat(cellFormat);
            sheet.addCell(tl);
            i++;
        }
        wwb.write();
        wwb.close();
        try{
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}