package 极客并发测试.day42Actor模型_面向对象原生的并发模型;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 累加器 {
    public static void main(String[] args) throws InterruptedException {
        // 创建 Actor 系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        //4 个线程生产消息
        ExecutorService es = Executors.newFixedThreadPool(4);
        //创建CounterActor
        ActorRef counterActor = system.actorOf(Props.create(CounterActor.class));
        // 生产 4*100000 个消息
        for (int i=0; i<4; i++) {
            es.execute(()->{
                for (int j=0; j<100000; j++) {
                    counterActor.tell(1, ActorRef.noSender());
                }
            });
        }
        // 关闭线程池
        es.shutdown();
        // 等待 CounterActor 处理完所有消息
        Thread.sleep(1000);
        // 打印结果
        counterActor.tell("", ActorRef.noSender());
        // 关闭 Actor 系统
//        system.shutdown();
    }
}

// 累加器
class CounterActor extends UntypedActor {
    private int counter = 0;
    @Override
    public void onReceive(Object message){
        // 如果接收到的消息是数字类型，执行累加操作，
        // 否则打印 counter 的值
        if (message instanceof Number) {
            counter += ((Number) message).intValue();
        } else {
            System.out.println(counter);
        }
    }
}