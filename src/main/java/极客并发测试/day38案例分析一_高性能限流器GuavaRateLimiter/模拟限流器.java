package 极客并发测试.day38案例分析一_高性能限流器GuavaRateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 模拟限流器 {
    static long prev;
    public static void main(String[] args) {
        // 限流器流速：2 个请求 / 秒
        RateLimiter limiter =
                RateLimiter.create(10);

        // 执行任务的线程池
        ExecutorService es = Executors
                .newFixedThreadPool(1);
        // 记录上一次执行时间
        prev = System.nanoTime();
        // 测试执行 20 次
        for (int i=0; i<20; i++){
            // 限流器限流
            limiter.acquire();
            SimpleDateFormat s=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SS");
            // 提交任务异步执行
            es.execute(()->{
                long cur=System.nanoTime();
                // 打印时间间隔：毫秒
                System.out.println(
                        (cur-prev)/1000_000+"    "+Thread.currentThread()+"  "+s.format(new Date()));
                prev = cur;
            });
        }

    }
}
