package 极客并发测试.day35两阶段终止模式_优雅地终止线程;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 线程池强制停止_接受返回 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newFixedThreadPool(2);
        es.execute(new test("runnable 1"));
        es.execute(new test("runnable 2"));
        //因为只有两个线程，所以第三个任务，会进入阻塞队列
        es.execute(new test("runnable 3,取出阻塞队列中的任务"));
        Thread.sleep(3000l);
        System.out.println("3秒后退出线程池");
        List<Runnable> runnables = es.shutdownNow();
        for (Runnable rb:runnables) {
            System.out.println("===保留已经提交未执行的任务========"+((test)rb).name);
        }
        es.execute(new test("runnable 4"));
        es.execute(new test("runnable 5"));

    }
    static class test implements Runnable {
        String name ;
        public test(String s) {
            this.name=s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
