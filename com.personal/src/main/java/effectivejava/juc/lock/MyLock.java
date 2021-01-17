package effectivejava.juc.lock;

/**
 * @description: java基于对象的锁。
 * @version: 1.0
 * @date: 2021-01-17 10:08:02
 * @author: wanglong16@meicai.cn
 */
public class MyLock {

    private int order = 0;

    private static int staticOrder = 0;


    public int getOrder() {
        return order;
    }

    public static int getStaticOrder() {
        return staticOrder;
    }

    private void printOrder() {
        System.out.println("order 当前值为" + order);
    }

    private static void printStaticOrder() {
        System.out.println("static order 当前值为" + staticOrder);
    }

    /**
     * synchronized 基于当前类对象的锁
     * @param i
     */
    public void orderPlus(int i) {
        System.out.println(String.format("当前线程：%s 给变量增加了【%s】",Thread.currentThread().getName(),i));
        order += i;
        printOrder();
    }

    /**
     * synchronized 基于当前类产生的实例对象的锁
     * @param i
     */
    public synchronized void syncOrderPlus(int i) {
        System.out.println(String.format("当前线程：%s 给变量增加了【%s】",Thread.currentThread().getName(),i));
        order += i;
        printOrder();
    }

    /**
     * synchronized 基于当前类产生的实例对象的锁 和上边方法相同，锁定当前对象
     * @param i
     */
    public void syncOrderPlusV1(int i) {
        synchronized (this) {
            System.out.println(String.format("当前线程：%s 给变量增加了【%s】",Thread.currentThread().getName(),i));
            order += i;
        }
        printOrder();
    }

    /**
     * synchronized 基于当前【类对象】的锁 和上边方法相同，锁定当前【类】对象、
     * 类对象是一个特殊的对象
     * @param i
     */
    public void syncOrderPlusV2(int i) {
        synchronized (MyLock.class) {
            System.out.println(String.format("当前线程：%s 给变量增加了【%s】",Thread.currentThread().getName(),i));
            order += i;
        }
        printOrder();
    }


    /**
     * synchronized 基于当前【类对象】的锁，只能锁定当前【类】对象、由于static修饰类当前方法，需要操作类中的静态变量
     * 类对象是一个特殊的对象
     * @param i
     */
    public synchronized static void staticSyncOrderPlus(int i) {
        System.out.println(String.format("当前线程：%s 给静态变量增加了【%s】",Thread.currentThread().getName(),i));
        staticOrder += i;
        printStaticOrder();
    }

    /**
     * synchronized 基于当前【类对象】的锁，只能锁定当前【类】对象、由于static修饰类当前方法，需要操作类中的静态变量
     * 类对象是一个特殊的对象
     * @param i
     */
    public static void staticSyncOrderPlusV1(int i) {
        synchronized (MyLock.class) {
            System.out.println(String.format("当前线程：%s 给静态变量增加了【%s】",Thread.currentThread().getName(),i));
            staticOrder += i;
        }
        printStaticOrder();
    }
}
