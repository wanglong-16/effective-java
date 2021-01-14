package effectivejava.juc;

import java.util.stream.IntStream;

/**
 * @description: 线程状态、线程组、线程优先级
 * @version: 1.0
 * @date: 2021-01-12 23:12:05
 * @author: wanglong16@meicai.cn
 */
public class MyThreadStatesAndPriority {

    static class CyclePrintOut implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(String.format("线程组：%s, 线程名称：%s, 线程优先级：%s, 执行到 %s 次",
                        Thread.currentThread().getThreadGroup().getName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(), i + 1));
            }
        }
    }

    /**
     * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。
     * 而真正的调用顺序，是由操作系统的线程调度算法决定的。
     *
     * Java提供一个线程调度器来监视和控制处于RUNNABLE状态的线程。线程的调度策略采用抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。
     * 在优先级相同的情况下，按照“先到先得”的原则。每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程
     *
     * 总结来说，线程组是一个树状的结构，每个线程组下面可以有多个线程或者线程组。
     * 线程组可以起到统一控制线程的优先级和检查线程的权限的作用。子线程的优先级总是不大于父线程的优先级
     */

    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new CyclePrintOut());
            thread.setPriority(i);
            thread.start();
        });
    }

    /**
     * 在现在的操作系统中，线程是被视为轻量级进程的，所以操作系统线程的状态其实和操作系统进程的状态是一致的。
     *
     * 操作系统线程主要有以下三个状态：
     *
     * 就绪状态(ready)：线程正在等待使用CPU，经调度程序调用之后可进入running状态。
     * 执行状态(running)：线程正在使用CPU。
     * 等待状态(waiting): 线程经过等待事件的调用或者正在等待其他资源（如I/O）。
     *
     * 线程的6种状态
     */
     // Thread.State 源码
     public enum State {
         NEW,  //处于NEW状态的线程此时尚未启动。这里的尚未启动指的是还没调用Thread实例的start()方法。
         RUNNABLE, //Java线程的RUNNABLE状态其实是包括了传统操作系统线程的ready和running两个状态的。
         BLOCKED,  //阻塞状态。处于BLOCKED状态的线程正等待锁的释放以进入同步区。
         WAITING,  //等待状态。处于等待状态的线程变成RUNNABLE状态需要其他线程唤醒。 调用如下3个方法会使线程进入等待状态：
        /**
         Object.wait()：使当前线程处于等待状态直到另一个线程唤醒它；
         Thread.join()：等待线程执行完毕，底层调用的是Object实例的wait方法；
         LockSupport.park()：除非获得调用许可，否则禁用当前线程进行线程调度。*/
         TIMED_WAITING, // 超时等待状态。线程等待一个具体的时间，时间到后会被自动唤醒。
        /**
         * Thread.sleep(long millis)：使当前线程睡眠指定时间；
         * Object.wait(long timeout)：线程休眠指定时间，等待期间可以通过notify()/notifyAll()唤醒；
         * Thread.join(long millis)：等待当前线程最多执行millis毫秒，如果millis为0，则会一直执行；
         * LockSupport.parkNanos(long nanos)： 除非获得调用许可，否则禁用当前线程进行线程调度指定时间；
         * LockSupport.parkUntil(long deadline)：同上，也是禁止线程进行调度指定时间；
         */
         TERMINATED; //终止状态。此时线程已执行完毕。 标示为2，也是很多时候debug或者手动中断的返回结果
     }

    /**
     * TIMED_WAITING与WAITING状态类似，只是TIMED_WAITING状态等待的时间是指定的。
     *
     * Thread.sleep(long)
     *
     * 使当前线程睡眠指定时间。需要注意这里的“睡眠”只是暂时使线程停止执行，并不会释放锁。时间到后，线程会重新进入RUNNABLE状态。
     *
     * Object.wait(long)
     *
     * wait(long)方法使线程进入TIMED_WAITING状态。这里的wait(long)方法与无参方法wait()相同的地方是，都可以通过其他线程调用notify()或notifyAll()方法来唤醒。
     *
     * 不同的地方是，有参方法wait(long)就算其他线程不来唤醒它，经过指定时间long之后它会自动唤醒，拥有去争夺锁的资格。
     *
     * Thread.join(long)
     *
     * join(long)使当前线程执行指定时间，并且使线程进入TIMED_WAITING状态。
     */



    /**
     *  ### 启动线程的方法 Thread.start() 不可重复调用 ###
     *  这个方法的理解应当是，在代码层面唤起操作系统对这个线程的处理，执行以后就加入到操作系统内部的处理逻辑中去了
     *  后续执行由操作系统来决定其状态。重复调用只会由于状态检测而抛出异常
     *
     *  public synchronized void start() {
     *     if (threadStatus != 0)
     *         throw new IllegalThreadStateException();
     *
     *     group.add(this);
     *
     *     boolean started = false;
     *     try {
     *         start0();
     *         started = true;
     *     } finally {
     *         try {
     *             if (!started) {
     *                 group.threadStartFailed(this);
     *             }
     *         } catch (Throwable ignore) {
     *
     *         }
     *     }
     * }
     *
     *  // Thread.getState方法源码：
     * public State getState() {
     *     // get current thread state
     *     return sun.misc.VM.toThreadState(threadStatus);
     * }
     *
     * // sun.misc.VM 源码：
     * public static State toThreadState(int var0) {
     *     if ((var0 & 4) != 0) {
     *         return State.RUNNABLE;
     *     } else if ((var0 & 1024) != 0) {
     *         return State.BLOCKED;
     *     } else if ((var0 & 16) != 0) {
     *         return State.WAITING;
     *     } else if ((var0 & 32) != 0) {
     *         return State.TIMED_WAITING;
     *     } else if ((var0 & 2) != 0) {
     *         return State.TERMINATED;
     *     } else {
     *         return (var0 & 1) == 0 ? State.NEW : State.RUNNABLE;
     *     }
     * }
     *
     * 在调用一次start()之后，threadStatus的值会改变（threadStatus !=0），此时再次调用start()方法会抛出IllegalThreadStateException异常。
     *
     * 比如，threadStatus为2代表当前线程状态为TERMINATED。
     */
}
