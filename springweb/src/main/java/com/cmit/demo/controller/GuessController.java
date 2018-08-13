package com.cmit.demo.controller;

import com.cmit.demo.entity.Guess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Random;

/**
 * @Author: chenjk
 * @Description: 游戏控制类
 * @Date:Created in 21:15 2018/8/8
 */
@Controller
public class GuessController {
    int num = (int)(Math.random()*100);
    @RequestMapping("/doGame")
    public String doGame(Guess guess) {
        guess.setNumber(guess.getNumber());
        guess.setGuessTip(getInformation(guess.getNumber()));
        return "game.html";
    }

    @RequestMapping("/doAgain")
    public String doAgain(Guess guess){
        num =(int)(Math.random()*100);
        guess.setGuessTip("请输入0到100的整数");
        return  "game.html";
    }


    private String getInformation(int number) {
        String msg= "";
        if (Integer.toString(number) == ""||(number<0||number>100)) {
            msg = "请输入0-100的合法数字!";
        }else if (number == num) {
            msg = "牛啊,一下子就猜中了";
        } else if (number > num) {
            msg = "请输入0-" + number + "的整数！";
        } else {
           msg = "请输入" + number + "-100的整数！";
        }
        return msg;

    }
}
