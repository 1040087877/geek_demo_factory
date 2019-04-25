package 极客并发测试.day24CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class 异常处理 {
    public static void main(String[] args) {
        CompletableFuture<Integer> fo=CompletableFuture.supplyAsync(()->{

            return 1/0;
        }).thenApply(r->r+10).exceptionally(e->0)
                .handle(new BiFunction<Integer, Throwable, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Throwable throwable) {
                        return integer;
                    }
                });
                /*.whenComplete(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer s, Throwable throwable) {
                        System.out.println(s);
                    }
                });*/
                /*.handle((__,tf)->{
                    tf.printStackTrace();;
                    return 0;
                });;*/
//                .handle((__, tf)->{
//                    return tf+"";
//        });
        System.out.println(fo.join());
    }
}
