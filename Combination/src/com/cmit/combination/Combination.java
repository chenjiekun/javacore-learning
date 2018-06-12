package com.cmit.combination;

import com.cmit.util.RondomCombination;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenjk
 * @Description:
 * 1、从1到31中任选三个不重复数的进行组合，求所有组合
 * 2、计算组合的平均值，并求出平均值出现的次数，以及出现概率。 例如， 1,2,3， 平均值为 2， 出现1次， 出现概率 = 1 / 所有组合数目。
 * 3、输出 csv格式数据：平均值，出现次数，所占百分比，例如   2 ， 1， %
 * @Date:Created in 11:33 2018/6/11
 */
public class Combination {
    //输出 csv格式数据：平均值，出现次数，所占百分比，例如   2 ， 1， %
    public static void outputCSV(Map<Double,Integer> probability) {
        File file = new File("E:\\java project\\Combination\\src\\CsvFile\\combination.csv");
        //构建输出流，同时指定编码
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), "gbk");

            //写头文件，写完后换行
            String[] titles = {"平均值","出现次数","所占百分比"};
            for(String title : titles){
                ow.write(title);
                ow.write(",");
            }
            ow.write("\r\n");

            //写主体数据
            float sumTimes = 0;
            for(float times:probability.values())
                sumTimes += times;
            for(Double avg:probability.keySet()) {
                ow.write(avg+"");
                ow.write(",");
                ow.write(probability.get(avg)+"");
                ow.write(",");
                ow.write((probability.get(avg)/(float)sumTimes)*100+"%");
                ow.write("\r\n");
            }
            try {
                ow.flush();
                ow.close();
                System.out.println("-------CSV文件已经写入--------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void  main(String[] args ) {
        RondomCombination rondomCombination = new RondomCombination(31,3);
        rondomCombination.getCombinationList();
        outputCSV(rondomCombination.getProbability());
    }

}
