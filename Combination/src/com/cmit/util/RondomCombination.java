package com.cmit.util;

import com.sun.istack.internal.localization.NullLocalizable;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 11:31 2018/6/11
 */
public class RondomCombination {
    private int combination;
    private int countnumber;
    private int sum;
    private int result = 1;
    private int result1 = 1;
    private List<List<Integer>> combinationList;
    private Map<Double,Integer> probability;

    public RondomCombination(int combination, int countnumber) {
        this.combination = combination;
        this.countnumber = countnumber;
        }
        //从任意数中任选N(N>3)个不重复数的进行组合，求所有组合
        public List<List<Integer>> getCombinationList(){
            combinationList = new ArrayList<List<Integer>>();
            for (int i = 0; i <= (combination-countnumber+1) ; i++) {
                for (int j = i+1; j <= (combination-countnumber+2); j++) {
                    for (int k = j+1; k < (combination-countnumber+3); k++) {
                        List<Integer> tempList = new ArrayList<Integer>() ;
                        tempList.add(i);
                        tempList.add(j);
                        tempList.add(k);
                        combinationList.add(tempList);
                    }

                    for(int z =0;z<combinationList.size();z++) {
                        System.out.println(combinationList.get(z));
                    }
                    System.out.println("从1到"+ combination +"中任选" + countnumber+ "不重复数的进行组合，组合总数为:" + combinationList.size());
                }
            }
        return combinationList;
        }
    //计算组合的平均值，并求出平均值出现的次数，以及出现概率。
    public Map<Double, Integer> getProbability() {
        probability = new HashMap<Double, Integer>();

        for (int i=0;i<combinationList.size();i++) {
            double avecom = (double)(combinationList.get(i).get(0)+combinationList.get(i).get(1)+combinationList.get(i).get(2))/(double)3;
            if (probability.get(avecom) == null){
                probability.put(avecom,1);
            }else {
                int temavecom = probability.get(avecom);
                probability.remove(avecom);
                probability.put(avecom,temavecom+1);
            }
        }

        return probability;
    }
}


