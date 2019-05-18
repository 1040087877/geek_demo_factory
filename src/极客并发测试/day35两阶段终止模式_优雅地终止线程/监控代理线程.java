package 极客并发测试.day35两阶段终止模式_优雅地终止线程;

public class 监控代理线程 {
    public static void main(String[] args) throws InterruptedException {
        Proxy proxy = new Proxy();
        proxy.start();
        System.out.println("休息4秒后退出");
        Thread.sleep(4000);
        proxy.stop();
    }

    static class Proxy {
        boolean started = false;
        // 采集线程
        Thread rptThread;
        // 启动采集功能
        synchronized void start(){
            // 不允许同时启动多个采集线程
            if (started) {
                return;
            }
            started = true;
            rptThread = new Thread(()->{
                while (!Thread.currentThread().isInterrupted()){
                    // 省略采集、回传实现
                    report();
                    // 每隔两秒钟采集、回传一次数据
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){
                        // 重新设置线程中断状态
                        Thread.currentThread().interrupt();
                    }
                }
                // 执行到此处说明线程马上终止
                started = false;
            });
            rptThread.start();
        }

        private void report() {
            System.out.println("采集");
            System.out.println("回传");
        }

        // 终止采集功能
        synchronized void stop(){
            rptThread.interrupt();
        }
    }

}
