package effectivejava.juc.threads;

/**
 * @description: 锁和同步， synchronized, volatile
 * @version: 1.0
 * @date: 2021-01-12 23:12:50
 * @author: wanglong16@meicai.cn
 */
public class MyThreadWithLock implements Runnable{

    private int lockVal = 0;

    @Override
    public void run() {
        lockVal ++;
        try {
            System.out.println(String.format("当前lockVal值 %s", lockVal));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadWithLock myThreadWithLock1 = new MyThreadWithLock();
        //todo check lock
    }

}
