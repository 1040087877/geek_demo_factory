package 极客并发测试.day40案例分析三_高性能队列Disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class 自定义Disruptor {
    public static void main(String[] args) throws InterruptedException {
        // 指定 RingBuffer 大小,
        // 必须是 2 的 N 次方
        int bufferSize = 1024;
        // 构建 Disruptor
        Disruptor<LongEvent> disruptor=new Disruptor<LongEvent>(
                LongEvent::new,bufferSize, DaemonThreadFactory.INSTANCE);
        // 注册事件处理器--接收时间
        disruptor.handleEventsWith((event,sequence,endOfBatch) ->
                System.out.println("E:"+event));
        // 启动 Disruptor
        disruptor.start();

        //获取RingBuffer
        RingBuffer<LongEvent> ringBuffer=disruptor.getRingBuffer();
        //生产Event
        ByteBuffer bb=ByteBuffer.allocate(8);
        for (long l=0;true;l++){
            bb.putLong(0,1);
            //生产者生产消息
            ringBuffer.publishEvent((event,sequence,buffer)->
                    event.set(buffer.getLong(0)),bb);
            Thread.sleep(1000);
        }
    }

}
// 自定义 Event
class LongEvent {
    private long value;
    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}

