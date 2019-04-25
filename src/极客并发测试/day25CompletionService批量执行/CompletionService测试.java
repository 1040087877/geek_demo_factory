package 极客并发测试.day25CompletionService批量执行;

import java.util.concurrent.*;

public class CompletionService测试 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor= Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs=new ExecutorCompletionService<>(executor);
        //耗时操作1
        cs.submit(()->getPriceByS1());
        cs.submit(()->getPriceByS2());
        cs.submit(()->getPriceByS3());
        // 将询价结果异步保存到数据库
        for (int i=0;i<3;i++){
            Integer r=cs.take().get();
            executor.execute(()->save(r));
        }
    }

    private static void save(Integer r) {
        System.out.println("我保存了"+r);
    }

    private static Integer getPriceByS3() throws InterruptedException {
        Thread.sleep(5000L);
        return 5;
    }


    private static Integer getPriceByS2() throws InterruptedException {
        Thread.sleep(1000l);
        return 1;
    }

    private static Integer getPriceByS1() throws InterruptedException {
        Thread.sleep(3000l);
        return 3;
    }
}
