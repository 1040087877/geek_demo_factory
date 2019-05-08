package 极客并发测试.day26Fork_Join单机版的MapReduce;/**
 * @ClassName:
 * @Description: TODO
 * @author linyb3
 * @date
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName:
 * @Description: TODO
 * @author linyb3
 * @date
 *
 */
public class compute_join区别 {
    public static void main(String[] args) {
        // 创建分治任务线程池，参数为线程并发的个数
        ForkJoinPool fjp=new ForkJoinPool(4);
        // 创建分治任务,参数为斐波那契的n值
        Test test=new Test();
        long start=System.currentTimeMillis();
        //启动分治任务
        Integer result = fjp.invoke(test);
        //开启子线程
//        test.fork();
//        Integer compute = test.compute();
        long end =System.currentTimeMillis();
        //计算耗时
        System.out.println((end-start)+"毫秒");
        //输出结果
        System.out.println("计算结果："+result);
    }


}

//递归任务
class Test extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        try {
            Thread.sleep(4000l);
            System.out.println("当前线程："+Thread.currentThread());
            this.fork();
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }
}
