package 极客并发测试.day42Actor模型_面向对象原生的并发模型;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloActor模型 {
    public static void main(String[] args) {
        //创建Actor系统
        ActorSystem system=ActorSystem.create("HelloSystem");
        //创建HelloActor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        //发送消息给HelloActor
        helloActor.tell("Actor",ActorRef.noSender());
    }
}
class HelloActor extends UntypedActor{

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("接受到消息拉:"+message);
    }
}
