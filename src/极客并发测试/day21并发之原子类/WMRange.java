package 极客并发测试.day21并发之原子类;

import java.util.concurrent.atomic.AtomicReference;

public class WMRange {
        final int upper;
        final int lower;
        WMRange(int upper,int lower){
            this.upper=upper;
            this.lower=lower;
        }
       /* final AtomicReference<WMRange> rf = new AtomicReference<>(
                new WMRange(0,0)
        );
        // 设置库存上限
        void setUpper(int v){
            WMRange nr;
            WMRange or = rf.get();
            do{
                // 检查参数合法性
                if(v < or.lower){
                    throw new IllegalArgumentException();
                }
                nr = new
                        WMRange(v, or.lower);
            }while(!rf.compareAndSet(or, nr));
        }*/

     public   static   WMRange wmRange=null;
    public static void main(String[] args) throws InterruptedException {
        final AtomicReference<WMRange>
                rf = new AtomicReference<>(
                new WMRange(0,0)
        );

        wmRange = rf.get();
        Thread.sleep(1000l);

        new Thread(){
            @Override
            public void run() {
                WMRange nr = new
                        WMRange(14, 2);
                /*wmRange= rf.get();
                System.out.println(wmRange);*/
                WMRange or = rf.get();
                boolean b = rf.compareAndSet(or, nr);
                System.out.println(or);
                or = rf.get();
                boolean b1 = rf.compareAndSet(or, nr);
                System.out.println(b1);

            }
        }.start();




    }

    @Override
    public String toString() {
        return "WMRange{" +
                "upper=" + upper +
                ", lower=" + lower +
                '}';
    }
}
