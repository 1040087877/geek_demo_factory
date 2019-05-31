package 极客并发测试.day25CompletionService批量执行;

import java.util.Date;
import java.util.concurrent.*;

/**
 * CompletionService 接口方法的测试
 */
public class CompletionService接口方法 {
    public static void main(String[] args) throws InterruptedException, Exception {
        ExecutorService executor= Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs=new ExecutorCompletionService<>(executor);
        //耗时操作1
        cs.submit(()->getPriceByS1());
        cs.submit(()->getPriceByS2());
        cs.submit(()->getPriceByS3());
        System.out.println("当前时间"+new Date());
        // 将询价结果异步保存到数据库
        for (int i=0;i<3;i++){
            //取出的时候进行等待两秒时间，没有2秒内没有则返回NULL
            Future<Integer> poll = cs.poll(1, TimeUnit.SECONDS);
            Integer r = null;
            if(poll!=null){
                r = poll.get();
            }

            System.out.println("测试得到"+r+"     当前时间："+new Date());
            //转换对象，否则不能提交到lambda表达式
            Integer z=r;
            executor.execute(()->save(z));
        }
    }

    private static void save(Integer r) {
        System.out.println("我保存了"+r);
    }

    private static Integer getPriceByS3() throws InterruptedException {
        Thread.sleep(5000L);
        return 5;
    }


    private static Integer getPriceByS2() throws InterruptedException {
        Thread.sleep(1000l);
        return 1;
    }

    private static Integer getPriceByS1() throws InterruptedException {
        Thread.sleep(3000l);
        return 3;
    }
}
