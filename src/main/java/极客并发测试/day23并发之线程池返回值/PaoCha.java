package 极客并发测试.day23并发之线程池返回值;

import java.util.concurrent.*;

public class PaoCha {

    public static class T1Task implements Runnable{
        private FutureTask<T2Task> t2Task;
        T1Task(FutureTask<T2Task> t2Task) {
            this.t2Task = t2Task;
        }
        @Override
        public void run() {
            try {
                System.out.println( "T1:洗水壶");
                TimeUnit.SECONDS.sleep(1);
                System.out.println( "T1:烧开水");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(t2Task.get());
                System.out.println( "T1:拿到茶叶");

                System.out.println( "T1:泡茶");
                System.out.println( "上茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
    public static class T2Task implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println( "T2:洗茶壶");
                TimeUnit.SECONDS.sleep(1);
                System.out.println( "T2:洗茶杯");
                TimeUnit.SECONDS.sleep(2);
                System.out.println( "T2:拿茶叶");
                TimeUnit.SECONDS.sleep(1);
                System.out.println( "T2:龙井");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T2Task t2 = new T2Task();
        FutureTask t2TaskFuture = new FutureTask(t2,new Object());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(t2TaskFuture);

        T1Task t1 = new T1Task(t2TaskFuture);
        FutureTask<Object> t1TaskFuture = new FutureTask(t1,new Object());
        executorService.submit(t1TaskFuture);

    }

}