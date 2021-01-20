package effectivejava.juc.lock;

/**
 * @description: java基于对象的锁 - synchronized。
 * @version: 1.0
 * @date: 2021-01-17 10:08:02
 * @author: wanglong16@meicai.cn
 */
public class Synchronized {

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
        synchronized (Synchronized.class) {
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
        synchronized (Synchronized.class) {
            System.out.println(String.format("当前线程：%s 给静态变量增加了【%s】",Thread.currentThread().getName(),i));
            staticOrder += i;
        }
        printStaticOrder();
    }

    //=============================================

    /**
     *  锁的四种状态：
     * 无锁状态
     * 偏向锁状态
     * 轻量级锁状态
     * 重量级锁状态
     *
     */


     /**
     * Hotspot的作者经过以往的研究发现大多数情况下锁不仅不存在多线程竞争，而且总是由同一线程多次获得，
     * 于是引入了偏向锁。
     *
     * 偏向锁升级为轻量级锁：
     * 偏向锁使用了一种等到竞争出现才释放锁的机制，所以当其他线程尝试竞争偏向锁时， 持有偏向锁的线程才会释放锁。
     * 偏向锁升级成轻量级锁时，会暂停拥有偏向锁的线程，重置偏向锁标识，这个过程看起来容易，实则开销还是很大的，大概的过程如下：
     * 在一个安全点（在这个时间点上没有字节码正在执行）停止拥有锁的线程。
     * 遍历线程栈，如果存在锁记录的话，需要修复锁记录和Mark Word，使其变成无锁状态。
     * 唤醒被停止的线程，将当前锁升级成轻量级锁。
     */


    /**
     * 轻量级锁的加锁
     * JVM会为每个线程在当前线程的栈帧中创建用于存储锁记录的空间，我们称为Displaced Mark Word。
     * 如果一个线程获得锁的时候发现是轻量级锁，会把锁的Mark Word复制到自己的Displaced Mark Word里面。
     * 然后线程尝试用CAS将锁的Mark Word替换为指向锁记录的指针。如果成功，当前线程获得锁，如果失败，
     * 表示Mark Word已经被替换成了其他线程的锁记录，说明在与其它线程竞争锁，当前线程就尝试使用自旋来获取锁。
     */

    /**
     * 轻量级锁的释放：
     * 在释放锁时，当前线程会使用CAS操作将Displaced Mark Word的内容复制回锁的Mark Word里面。
     * 如果没有发生竞争，那么这个复制的操作会成功。如果有其他线程因为自旋多次导致轻量级锁升级成了重量级锁，
     * 那么CAS操作会失败，此时会释放锁并唤醒被阻塞的线程。
     */

    /**
     * java 锁 升级降级流程：
     *
     * 每一个线程在准备获取共享资源时： 第一步，检查MarkWord里面是不是放的自己的ThreadId ,如果是，表示当前线程是处于 “偏向锁” 。
     *
     * 第二步，如果MarkWord不是自己的ThreadId，锁升级，这时候，用CAS来执行切换，新的线程根据MarkWord里面现有的ThreadId，通知之前线程暂停，之前线程将Markword的内容置为空。
     *
     * 第三步，两个线程都把锁对象的HashCode复制到自己新建的用于存储锁的记录空间，接着开始通过CAS操作， 把锁对象的MarKword的内容修改为自己新建的记录空间的地址的方式竞争MarkWord。
     *
     * 第四步，第三步中成功执行CAS的获得资源，失败的则进入自旋 。
     *
     * 第五步，自旋的线程在自旋过程中，成功获得资源(即之前获的资源的线程执行完成并释放了共享资源)，则整个状态依然处于 轻量级锁的状态，如果自旋失败 。
     *
     * 第六步，进入重量级锁的状态，这个时候，自旋的线程进行阻塞，等待之前线程执行完成并唤醒自己。
     */

}
