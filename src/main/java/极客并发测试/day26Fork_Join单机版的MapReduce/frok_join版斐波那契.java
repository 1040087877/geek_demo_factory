package 极客并发测试.day26Fork_Join单机版的MapReduce;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName:
 * @Description: TODO
 * @author linyb3
 * @date
 */

public class frok_join版斐波那契 {
    public static void main(String[] args) {
        // 创建分治任务线程池，参数为线程并发的个数
        ForkJoinPool fjp=new ForkJoinPool(4);
        // 创建分治任务,参数为斐波那契的n值
        Fibonacci fib=new Fibonacci(20);
        long start=System.currentTimeMillis();
        //启动分治任务
        Integer result = fjp.invoke(fib);
        long end =System.currentTimeMillis();
        //计算耗时
        System.out.println((end-start)+"毫秒");
        //输出结果
        System.out.println("计算结果："+result);
    }
}
//递归任务
class Fibonacci extends RecursiveTask<Integer>{
    final int n;
    public Fibonacci(int n) {
        this.n=n;
    }

    @Override
    protected Integer compute() {
        if(n<=1){
            return n;
        }
        Fibonacci f1=new Fibonacci(n-1);
        //创建子任务去处理
        f1.fork();
        Fibonacci f2=new Fibonacci(n-2);
        // 等待子任务结果，并合并结果
        //当前任务执行处理
        Integer f2Int = f2.compute();
        //等待子任务的执行结果
        Integer f1Int = f1.join();
        //System.out.println("f1"+f1Int);
        return f2Int+f1Int;
    }
}
