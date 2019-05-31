package 极客并发测试.day33Thread_Per_Message模式_最简单的分工方法;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.LockSupport;

public class test01 {

    public test01() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(
                        new InetSocketAddress(8080));
        // 处理请求
        try {
            int i=0;
            while (true) {
                // 接收请求
                i++;
                SocketChannel sc = ssc.accept();
                System.out.println("第"+i+"个请求");
                // 每个请求都创建一个线程
                new Thread(()->{
                    try {
                        // 读 Socket
                        ByteBuffer rb = ByteBuffer
                                .allocateDirect(1024);
                        sc.read(rb);
                        // 模拟处理请求
                        Thread.sleep(4000);
                        // 写 Socket
                        ByteBuffer wb =
                                (ByteBuffer)rb.flip();
                        sc.write(wb);
                        // 关闭 Socket
                        sc.close();
                    }catch(Exception e){
                        throw new UncheckedIOException((IOException) e);
                    }
                }).start();
            }
        } finally {
            ssc.close();
        }


    }
}
