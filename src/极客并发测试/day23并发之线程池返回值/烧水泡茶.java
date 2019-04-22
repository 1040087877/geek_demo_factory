package 极客并发测试.day23并发之线程池返回值;

import java.util.concurrent.*;

public class 烧水泡茶 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2=new FutureTask<String>(new T2Task());
        FutureTask<String> ft1=new FutureTask<String>(new t1task(ft2));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(ft2);
        executorService.submit(ft1);
        System.out.println(ft1.get());
    }

    static class t1task implements Callable<String>{
        FutureTask<String> ft2;
        t1task(FutureTask<String> ft2){
            this.ft2=ft2;
        }
        @Override
        public String call() throws Exception {
            System.out.println("T1: 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1: 烧开水...");
            TimeUnit.SECONDS.sleep(15);
            // 获取 T2 线程的茶叶
            String tf =ft2.get();
            System.out.println("T1: 拿到茶叶:"+tf);

            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;
        }
    }

    // T2Task 需要执行的任务:
    // 洗茶壶、洗茶杯、拿茶叶
    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2: 洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2: 拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return " 龙井 ";
        }
    }
}
