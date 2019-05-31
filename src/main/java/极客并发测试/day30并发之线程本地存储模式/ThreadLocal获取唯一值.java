package 极客并发测试.day30并发之线程本地存储模式;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadLocal获取唯一值 {
    public static void main(String[] args) {
        long l = ThreadId.get();
        new Thread(){
            @Override
            public void run() {
                System.out.println(ThreadId.get());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(ThreadId.get());
            }
        }.start();
    }
    static class ThreadId {
        static final AtomicLong
                nextId=new AtomicLong(0);
        // 定义 ThreadLocal 变量
        static final ThreadLocal<Long>
                tl=ThreadLocal.withInitial(
                ()->nextId.getAndIncrement());
        // 此方法会为每个线程分配一个唯一的 Id
        static long get(){
            return tl.get();
        }
    }

}
