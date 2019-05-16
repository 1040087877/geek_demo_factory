package 极客并发测试.day34WorkerThread模式_如何避免重复创建线程;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 线程池死锁现像 {
    public static void main(String[] args) throws InterruptedException {
        //L1、L2 阶段共用的线程池
        ExecutorService es1 = Executors.newFixedThreadPool(2);
       // ExecutorService es2 = Executors.newFixedThreadPool(2);

        //L1 阶段的闭锁
        CountDownLatch l1=new CountDownLatch(2);
        for (int i=0; i<2; i++){
            System.out.println("L1");
            // 执行 L1 阶段任务
            es1.execute(()->{
                //L2 阶段的闭锁
                CountDownLatch l2=new CountDownLatch(2);
                for (int j=0; j<2; j++){
                    es1.execute(()->{
                        System.out.println("L2");
                        l2.countDown();
                    });
                }
                // 等待 L2 阶段任务执行完
                try {
                    System.out.println("l2的当前进度："+l2.getCount());
                    //由于l2并没有执行，所以卡在这里
                    l2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ceds");
                l1.countDown();
            });
        }
        // 等着 L1 阶段任务执行完
        l1.await();
        System.out.println("end");

    }
}
