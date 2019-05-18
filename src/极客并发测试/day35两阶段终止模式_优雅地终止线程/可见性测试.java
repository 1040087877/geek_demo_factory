package 极客并发测试.day35两阶段终止模式_优雅地终止线程;

import javax.swing.event.ChangeListener;
import java.awt.*;

public class 可见性测试 {
    public static  int MY_INT = 0;
    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {

            int local_value=MY_INT;
            System.out.println(local_value+"===="+MY_INT);

            while (MY_INT < 5) {
                //使用sysout输出，MY_INF会更新
                System.out.println(local_value+"==="+MY_INT);
                System.out.println(local_value != MY_INT);

                if (local_value != MY_INT) {
                    System.out.println(local_value != MY_INT);
                    System.out.println(local_value+"===="+MY_INT);
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value=MY_INT;
                }
            }
        }
    }


    /**
     * 此线程负责改变MY_INT的值
     */
    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            while (MY_INT < 5) {
                System.out.println("Incrementing MY_INT to " + (MY_INT + 1));
                MY_INT = ++MY_INT;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


