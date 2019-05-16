package 极客并发测试.day34WorkerThread模式_如何避免重复创建线程;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch l1=new CountDownLatch(3);
        for (int i = 0; i < 4; i++) {
            l1.countDown();
            System.out.println(l1.getCount());
        }
    }
}
