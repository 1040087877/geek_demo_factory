package 极客并发测试.day22并发之线程池;

import java.util.Date;
import java.util.concurrent.*;

public class 线程池 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue queue=new LinkedBlockingQueue(7);
        ThreadPoolExecutor pool=new ThreadPoolExecutor(2, 2, 5, TimeUnit.DAYS, queue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t=new Thread(r);
                        t.setName("测试");
                        try {
                            Thread.sleep(3000l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return t;
                    }
                },new ThreadPoolExecutor.AbortPolicy());
        Future<?> submit = pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
                System.out.println(Thread.currentThread() + "" + new Date());
                try {
                    int i = 1 / 0;
                    Thread.sleep(3000l);
                    System.out.println("结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //获取异常信息
        submit.get();
        /*pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("as2dasd");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
        /*pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
}
