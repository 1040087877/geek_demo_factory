package 极客并发测试.day25CompletionService批量执行;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Future取消操作 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs =
                new ExecutorCompletionService<>(executor);
        // 提交异步任务，并保存 future 到 futures
        Future<Integer> f01 = cs.submit(() -> geocoderByS1());
        System.out.println(f01.get());
        System.out.println(f01.cancel(true));
        System.out.println(f01.isCancelled());
        System.out.println(f01.isDone());
    }


    private static Integer geocoderByS1() throws InterruptedException {
        Thread.sleep(10000l);
        return 10;
    }

}
