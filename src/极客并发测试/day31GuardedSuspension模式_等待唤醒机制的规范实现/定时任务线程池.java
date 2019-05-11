package 极客并发测试.day31GuardedSuspension模式_等待唤醒机制的规范实现;/**
 * @ClassName:
 * @Description: TODO
 * @author linyb3
 * @date
 */

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:
 * @Description: TODO
 * @author linyb3
 * @date
 *
 */
public class 定时任务线程池 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run "+ new Date()+"   "+Thread.currentThread());
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    class Test{
        volatile boolean inited = false;
        int count = 0;
        void init(){
            if(inited){
                return;
            }
            synchronized (Test.class){
                if(inited){
                    inited = true;
                    // 计算 count 的值
                    //count = calc();
                }
            }
        }
    }

}
