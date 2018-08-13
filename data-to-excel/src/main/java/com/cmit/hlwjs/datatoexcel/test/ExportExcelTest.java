package com.cmit.hlwjs.datatoexcel.test;

import com.cmit.hlwjs.datatoexcel.model.RptDataMmAp;
import jxl.Workbook;
import jxl.write.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 15:43 2018/7/4
 */
@Service
public class ExportExcelTest {
    public static void main(String[] args) {

        String[] titles ={"账期","AP代码","AP名称","服务费金额"};
        OutputStream os = null;
        WritableWorkbook wwb = null;
        List<RptDataMmAp> rptDataMmAps = null;
        try {
            String filePath = "D:\\TestExcel.xls";
            File file = new File(filePath);
            if(!file.isFile())//如果指定文件不存在，则新建该文件
                file.createNewFile();
            os = new FileOutputStream(file);//创建一个输出流
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
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
            for (int i = 0; i < titles.length; i++) {
                titleLabel = new Label(i, 1, titles[i]);
                titleLabel.setCellFormat(cellFormat);
                sheet.addCell(titleLabel);
            }

            int i = 2;
            Label tl = null;
            for (RptDataMmAp rptDataMmAp : rptDataMmAps) {//循环填充数据

                tl = new Label(0, i, rptDataMmAp.getApCode());
                tl.setCellFormat(cellFormat);
                sheet.addCell(tl);

                tl = new Label(1, i, rptDataMmAp.getApName());
                tl.setCellFormat(cellFormat);
                sheet.addCell(tl);

                tl = new Label(2, i, rptDataMmAp.getDataType());
                tl.setCellFormat(cellFormat);
                sheet.addCell(tl);
                i++;
            }

            wwb.write();//将内容写到excel文件中
            os.flush();//清空输出流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                if(wwb != null)
                    wwb.close();
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
        }
}