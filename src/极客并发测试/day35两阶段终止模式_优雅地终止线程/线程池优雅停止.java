package 极客并发测试.day35两阶段终止模式_优雅地终止线程;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 线程池优雅停止 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newFixedThreadPool(2);
        es.execute(new test("runnable 1"));
        es.execute(new test("runnable 2"));
        //因为只有两个线程，所以第三个任务，会进入阻塞队列
        es.execute(new test("runnable 3,取出阻塞队列中的任务"));
        Thread.sleep(3000l);
        System.out.println("3秒后退出线程池");
        es.shutdown();
        es.execute(new test("runnable 4"));
        es.execute(new test("runnable 5"));

    }
    static class test implements Runnable {
        String name ;
        public test(String s) {
            this.name=s;
        }

        @Override
        public void run() {
            int i=0;
            while (true){
                try {
                    i++;
                    if(i>10){
                        break;
                    }
                    Thread.sleep(1000l);
                    System.out.println(name+":打印日志");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
