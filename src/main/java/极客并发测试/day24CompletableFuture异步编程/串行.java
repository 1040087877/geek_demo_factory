package 极客并发测试.day24CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class 串行 {
    //串行
    public static void main(String[] args) {
        CompletableFuture<String> fo=CompletableFuture.supplyAsync(()->"Hello World")
                .thenApply(s->s+ "QQ")
                .thenApply(String::toLowerCase);
        System.out.println(fo.join());//等待并接收返回值
    }
}
