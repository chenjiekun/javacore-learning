package com.cmit.demo.entity;

/**
 * @Author: chenjk
 * @Description:
 * @Date:Created in 21:22 2018/8/8
 */
public class Guess {
    private int number;
    private String guessTip;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGuessTip() {
        return guessTip;
    }

    public void setGuessTip(String guessTip) {
        this.guessTip = guessTip;
    }

    @Override
    public String toString() {
        return "Guess{" +
                "number=" + number +
                ", guessTip='" + guessTip + '\'' +
                '}';
    }
}
