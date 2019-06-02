package 极客并发测试.day30并发之线程本地存储模式;

public class ThreadLocal获取遍历值 {
    public static void main(String[] args) {
            ThreadLocalTest local = new ThreadLocalTest();
            ThreadBean01 thread01 = new ThreadBean01("线程一",local);
            ThreadBean01 thread02 = new ThreadBean01("线程二",local);
            thread01.start();
            thread02.start();

    }
}
class NumberBean {
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class ThreadLocalTest {
    // 定义匿名子类创建ThreadLocal的变量为什么是静态的，不管你new多少个这个类，ThreadLocal只有一个
    private static ThreadLocal<NumberBean> seqNum = new ThreadLocal<NumberBean>() {
        // 覆盖初始化方法,泛型指定什么类型就返回什么类型，方法名不变自动调用。
        public NumberBean initialValue() {
            System.out.println("初始化了");
            return new NumberBean();
        }
    };

    // 或者这线程的数据
    public NumberBean getNextNum() {
        return seqNum.get();
    }

}

class ThreadBean01 extends Thread {
    private ThreadLocalTest te;

    public ThreadBean01(String name, ThreadLocalTest te) {
        super(name);
        this.te = te;
    }

    @Override
    public void run() {
        while (te.getNextNum().getId() < 10) {
            te.getNextNum().setId(te.getNextNum().getId() + 1);
            System.out.println(this.getName() + "数字： " + te.getNextNum().getId());
        }
    }

    public ThreadLocalTest getTe() {
        return te;
    }

    public void setTe(ThreadLocalTest te) {
        this.te = te;
    }
}