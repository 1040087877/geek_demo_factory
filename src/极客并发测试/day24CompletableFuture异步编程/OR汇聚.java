package 极客并发测试.day24CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OR汇聚 {
    public static void main(String[] args) {
        CompletableFuture<String> f1=new CompletableFuture<>().supplyAsync(()->{
           int t=5;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.valueOf(t);
        });
        CompletableFuture f2=CompletableFuture.supplyAsync(()->{
            int t=2;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.valueOf(t);
        });
        CompletableFuture<String> f3=f1.applyToEither(f2,s->s);
        System.out.println(f3.join());
    }
}
