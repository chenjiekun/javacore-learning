package com.cmit.exceltosql.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2018/7/18
 * @author chenjk
 *
 */
@Service
public class ReadExcel {

    private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);
    private Workbook wb = null;

    /**
     * 表格
     */
    private Sheet sheet;

    /**
     * 列
     */
    private Row row;

    /**
     * xls文件类型后缀
     */
    final private String xlsFile = ".xls";
    /**
     * xlsx文件类型后缀
     */
    final private String xlsxFile = ".xlsx";


    String filepath;
    @Autowired
    private ReadFileLists readFileLists;

//    /**
//     * 根据文件路径名读取excel文件
//     *
//     * @param filepath
//     *            文件路径名
//     */
//    public ReadExcel() {
//
//    }

    /**
     * 打开要读取的文件，获得wb对象
     */
    public void open() {
        if (this.filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if (xlsFile.equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (xlsxFile.equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * 读取Excel表格表头的内容
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // 表头内容为String
            title[i] = row.getCell(i).getStringCellValue();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, Map<Integer, Object>> readExcelContent() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();
        //获取Excel文档中的第一个表单
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    /**
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {
                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat(
                                "yyyy-MM");
                        cellvalue = formater.format(date);
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String temp = cell.getStringCellValue();
                        // 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                        if (temp.indexOf(".") > -1) {
                            cellvalue = String.valueOf(new Double(temp)).trim();
                        } else {
                            cellvalue = temp.trim();
                        }

                    }
                    break;
                }
                // 如果当前Cell的Type为STRING
                case STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

//    /**
//     * 将excel表格中的内容写入到文件中
//     *
//     * @param path
//     *            输出的文件路径
//     * @param content
//     *            excel表格中的内容
//     */
//    public void write2File(String path, String content) {
//
//        File file = new File(path);
//
//        try (FileOutputStream fop = new FileOutputStream(file)) {
//            // 如果文件不存在，就新建一个
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            // 以字节获取内容
//            byte[] contentInBytes = content.getBytes();
//            fop.write(contentInBytes);
//            fop.flush();
//            fop.close();
//            System.out.println("Write content to " + path + " success!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * 根据文件路径名读取excel文件
     *
     * @param filepath
     *            文件路径名
     */
    public Map<Integer, Map<Integer, Object>> readExcelFile(String filepath) {
        Map<Integer, Map<Integer, Object>> map = null;
        ArrayList<String> fileLists = readFileLists.readFileList(filepath);

        for (String filePath : fileLists) {

            setFilepath(filePath);
            open();

            // 读取Excel表格内容
            try {
                map = readExcelContent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

}
