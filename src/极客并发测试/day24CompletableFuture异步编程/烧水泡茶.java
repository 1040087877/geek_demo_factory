package 极客并发测试.day24CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class 烧水泡茶 {

    // 任务 1：洗水壶 -> 烧开水
    static CompletableFuture<Void> f1 =
            CompletableFuture.runAsync(()->{
                System.out.println("T1: 洗水壶...");
                sleep(2, TimeUnit.SECONDS);

                System.out.println("T1: 烧开水...");
                sleep(15, TimeUnit.SECONDS);
            });
    // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
    static CompletableFuture<String> f2 =
            CompletableFuture.supplyAsync(()->{
                System.out.println("T2: 洗茶壶...");
                sleep(1, TimeUnit.SECONDS);

                System.out.println("T2: 洗茶杯...");
                sleep(2, TimeUnit.SECONDS);

                System.out.println("T2: 拿茶叶...");
                sleep(1, TimeUnit.SECONDS);
                return " 龙井 ";
            });
    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }



    public static void main(String[] args) {
        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf)->{
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });

        // 等待任务 3 执行结果
        System.out.println("测试代码：");
        System.out.println("测试代码："+f3.join());
    }
}
