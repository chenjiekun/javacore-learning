import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

/**
 * @Author: chenjk
 * @Description: corejava one
 * @Date:Created in 10:22 2018/5/31
 */
public class InputTest {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
         //get first input
        System.out.println("What is your name?");
        String name = in.nextLine();

        //get second input
        System.out.println("How old are you?");
        int age = in.nextInt();

        //display output on console
        System.out.println("hello," + name + ",next year ,you will be " + (age+1));
    }
}
