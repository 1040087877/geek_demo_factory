package 极客并发测试.day22并发之线程池;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 线程池超过删除 {
    public static void main(String[] args) {
        LinkedBlockingQueue queue=new LinkedBlockingQueue(2);
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
        //任务1
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务1");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务2
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务2");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务3
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务3");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务4
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务4");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务5
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务5");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务6
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务6");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //任务7
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd任务7");
                System.out.println(Thread.currentThread()+""+new Date());
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
