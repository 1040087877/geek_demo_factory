package 极客并发测试.day30并发之线程本地存储模式;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocal获取时间Simpl {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println(System.identityHashCode(SafeDateFormat.get()));
                System.out.println(System.identityHashCode(SafeDateFormat.get()));
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println(System.identityHashCode(SafeDateFormat.get()));
            }
        }.start();
    }
    static class SafeDateFormat {
        // 定义 ThreadLocal 变量
        static final ThreadLocal<DateFormat>
                tl=ThreadLocal.withInitial(
                ()-> new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss"));

        static DateFormat get(){
            return tl.get();
        }
    }

}
