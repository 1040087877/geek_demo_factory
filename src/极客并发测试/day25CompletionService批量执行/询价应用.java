package 极客并发测试.day25CompletionService批量执行;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class 询价应用 {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
    // 异步向电商 S1 询价
        cs.submit(()->getPriceByS1());
    // 异步向电商 S2 询价
        cs.submit(()->getPriceByS2());
    // 异步向电商 S3 询价
        cs.submit(()->getPriceByS3());
        // 将询价结果异步保存到数据库
        // 并计算最低报价
        AtomicReference<Integer> m =
                new AtomicReference<>(Integer.MAX_VALUE);
        CountDownLatch latch=new CountDownLatch(3);
        for (int i=0; i<3; i++) {
            executor.execute(()->{
                Integer r = null;
                try {
                    r = cs.take().get();
                } catch (Exception e) {}
                save(r);
                //m.set(Integer.min(m.get(), r));
                //原子性处理
                Integer finalR = r;
                //第一种写法
                /*Integer andUpdate = m.getAndUpdate(new UnaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        return Integer.min(m.get(), finalR);
                    }
                });*/
                //lambda表达式的写法
                m.getAndUpdate(v->Integer.min(m.get(), finalR));

                //最小的记录
               // m.set(andUpdate);
                System.out.println("当前最小值"+m.get());
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("最终的最小值"+m.get());
    }

    private static void save(Integer r) {
        System.out.println("已经保存"+r);
    }

    private static Integer getPriceByS1() throws InterruptedException {
        Thread.sleep(6000l);
        return 1;
    }
    private static Integer getPriceByS2() throws InterruptedException {
        Thread.sleep(3000l);
        return 5;
    }
    private static Integer getPriceByS3() throws InterruptedException {
        Thread.sleep(5000L);
        return 3;
    }
}
