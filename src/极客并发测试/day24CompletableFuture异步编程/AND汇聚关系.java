package 极客并发测试.day24CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AND汇聚关系 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> fo = CompletableFuture.supplyAsync(() -> {
            return "asdasd";
        }).thenApply(s -> s);
        CompletableFuture<String> fo2=CompletableFuture.supplyAsync(()->{

            return "测试";
        });
        CompletableFuture<String> fo3 = fo.thenCombine(fo2, (__, t) -> { //fo等待fo2，所以fo2最后返回
            System.out.println("拿到" + t);
            return "加醋" + t;
        });
        System.out.println(fo3.join());
    }
}
