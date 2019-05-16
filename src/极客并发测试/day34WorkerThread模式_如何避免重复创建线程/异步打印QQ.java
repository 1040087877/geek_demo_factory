package 极客并发测试.day34WorkerThread模式_如何避免重复创建线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 异步打印QQ {
    public static void main(String[] args) {
        ExecutorService pool = Executors
                .newSingleThreadExecutor();
        pool.submit(() -> {
            try {
                String qq=pool.submit(()->"QQ").get();
                System.out.println(qq);
            } catch (Exception e) {
            }
        });

    }
}
