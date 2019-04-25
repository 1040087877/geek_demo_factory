package 极客并发测试.day25CompletionService批量执行;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ForkingCluster模拟 {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs =
                new ExecutorCompletionService<>(executor);
        // 用于保存 Future 对象
        List<Future<Integer>> futures =
                new ArrayList<>(3);
        // 提交异步任务，并保存 future 到 futures
        futures.add(
                cs.submit(()->geocoderByS1()));
        futures.add(
                cs.submit(()->geocoderByS2()));
        futures.add(
                cs.submit(()->geocoderByS3()));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则 break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                // 简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 取消所有任务
            for(Future<Integer> f : futures)
                f.cancel(true);
        }

        // 返回结果
        System.out.println("拿到了结果:"+r);
        //return r;

    }


    private static Integer geocoderByS1() throws InterruptedException {
        Thread.sleep(3000l);
        return 3;
    }
    private static Integer geocoderByS2() throws InterruptedException {
        Thread.sleep(1000l);
        return 1;
    }
    private static Integer geocoderByS3() throws InterruptedException {
        Thread.sleep(5000L);
        return 5;
    }

}
