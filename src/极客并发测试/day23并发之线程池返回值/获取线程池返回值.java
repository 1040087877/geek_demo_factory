package 极客并发测试.day23并发之线程池返回值;

import java.util.Date;
import java.util.concurrent.*;

public class 获取线程池返回值 {
    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        ThreadPoolExecutor pool=new ThreadPoolExecutor(2,3,5, TimeUnit.DAYS,new LinkedBlockingQueue<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Future<?> t1 = pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("休眠种"+new Date());
                try {
                    Thread.sleep(5000);
                    System.out.println("休眠结束"+new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("中断异常");
                }
            }
        });
        System.out.println(t1.get());
        System.out.println("获取到数据");
        System.out.println(t1.isCancelled());
        System.out.println(t1.isDone());
        Thread.sleep(2000l);
        System.out.println(t1.cancel(false));

        Future<?> t2 = pool.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                Thread.sleep(3000);
                System.out.println("线程2休眠结束"+new Date());
                return "测试";
            }
        });
        //System.out.println(t2.get(7,TimeUnit.SECONDS));//超时机制
        System.out.println(t2.get());
        System.out.println("华丽的分割线=============");
        Result t=new Result();
        t.setR("开始中");
        Future<Result> submit = pool.submit(new task(t), t);

        Result result = submit.get();
        System.out.println(result);
        System.out.println("华丽的分割线=============");
        FutureTask task=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return 1;
            }
        });
        pool.submit(task);
        System.out.println(task.get());


    }

    static class task implements Runnable{
        Result r;
        task(Result r){
            this.r=r;
        }
        @Override
        public void run() {
            r.setR("完成");
            r.getR();
        }
    }
    static class Result{
       public String r;

        public String getR() {
            return r;
        }

        public void setR(String r) {
            this.r = r;
        }

        @Override
        public String toString() {
            return r.toString();
        }
    }
}
