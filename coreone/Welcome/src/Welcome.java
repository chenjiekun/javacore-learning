/**
 * @Author: chenjk
 * @Description: corejava ch02
 * @Date:Created in 15:10 2018/5/30
 */
public class Welcome {
    public static void main(String[] args){
        String greeting = "Welcome to Core Java!";
        System.out.println(greeting);
        for (int i = 0; i < greeting.length() ; i++) {
            System.out.println("=");
            System.out.println();
        }
    }
}
