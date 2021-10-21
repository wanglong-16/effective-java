package effectivejava.juc.threads;

/**
 * @description: 多线程基础，thread 类和 runnable 接口
 * @version: 1.0
 * @date: 2021-01-12 06:10:45
 * @author: wanglong16@meicai.cn
 */
public class MySimpleThread {

    /**
     * 线程的 6 种状态：
     *
     * NEW       新创建状态
     * RUNNABLE  准备就绪，可执行状态
     * BLOCKED   由于其他线程获取阻塞对象监视器，从而无法进入同步区被阻塞, 此时线程进入到对象的阻塞队列 entryList 【去排队抢锁】
     * WAITING   在同步区调用了 thread.join, object.wait, LockSupport.park 等方法, 等待的线程将进入到对象的 waitSet 等待队列，等待其他线程中在同步区执行对象的notify 或者对象的 notifyAll 方法，
     *              此时将等待队列中的线程移动到阻塞队尾参与锁的竞争。
     * TIME_WAITING  超时等待，设置了超时时间的等待，线程只是在短暂的时间内失去抢锁的资格，一定时间后将再次进入阻塞队列 entryList 【去排队抢锁】
     * TERMAINTED  线程中断 / 线程中的任务已执行完毕。
     */

    public static void main(String[] args) {
        MySimpleThread st = new MySimpleThread();
        st.blockedTest();
    }


    public void blockedTest() {
        for (int i = 0; i < 9; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread thread = new Thread(this::manyThreadRun, "Thread-" + i);
            thread.start();
        }
    }

    /** exec result :
     *
     * Thread-0 own obj lock
     * Thread-2 own obj lock
     * Thread-8 own obj lock     id 大的先执行，而不是先排队
     * Thread-7 own obj lock
     * Thread-6 own obj lock
     * Thread-5 own obj lock
     * Thread-4 own obj lock
     * Thread-3 own obj lock
     * Thread-1 own obj lock
     *
     * synchronized 执行结果看 这意味着：内部使用的是非公平锁，即，当线程被阻塞后会优先去抢锁，
     */
    public synchronized void manyThreadRun() {
        if ("Thread-2".equals(Thread.currentThread().getName())) {
            try {
                Thread.sleep(1000); // sleep ： The thread does not lose ownership of any monitors
                System.out.println(" -- 当前活跃的线程数：" + Thread.activeCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " own obj lock");
    }


    public void waitingTest() {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                // 注意调用对象的wait waiting / notify notifyAll 等方法必须持有对象的监视器，
                // 也就是必须在synchronized 内执行 这些方法。
                try {
                    System.out.println("t1 is waiting");
                    lock.wait();
                    System.out.println("t1 is not wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock) {
                    Thread.sleep(3000);
                    lock.notify();
                    System.out.println("t2 notify obj");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    public void yieldTest() {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "running");
                        if (i % 10 == 0) {
                            System.out.println(Thread.currentThread().getName() + " -- " + i + "yield cpu");
                            Thread.yield();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "running");
            }
        }).start();

    }

    /**
     * 通过继承thread，并重写run方法来实现多线程，
     *     @Override
     *     public void run() {
     *         if (target != null) {
     *             target.run();
     *         }
     *     }
     *
     *     private Runnable target;
     *
     * jdk的线程类内部封装了线程相关的各种变量，本质也是调用一个Runnable接口对象的run方法
     *
     * 线程类是jdk封装的相对复杂的类，内部包括、线程的优先级，当前线程的状态，当前线程是否为守护线程
     */
    private static class ExtThread extends Thread {

        @Override
        public void run() {
            int start = 1;
            while (start <= 100) {
                System.out.println(String.format("继承Thread类：第%s次调用！", start));
                start ++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

    }

    public void testCatchRuntimeException() {

        try {
            // ** 父线程/外层线程是无法通过 try catch 捕获和处理子线程抛出的异常的，JVM会自动忽略抛出的异常，并正常执行后续代码
            // 除非显示设置异常处理句柄，才能在外部线程处理内部线程的异常
            Thread exThread = new Thread(() -> {
                System.out.println("抛出异常");
                throw new ArrayIndexOutOfBoundsException("数组越界");
            });

            Thread.setDefaultUncaughtExceptionHandler((t, e) -> System.out.println("通过静态方法捕获了" + t.getName() + e.getMessage()));
            //方法一、通过调用静态方法设置默认异常处理句柄
            exThread.setUncaughtExceptionHandler((t, e) -> System.out.println("通过实例捕获了" + t.getName() + e.getMessage()));
            //方法二、通过实例方法设置子线程的处理异常句柄 -- 会覆盖默认句柄处理
            exThread.start();
            exThread.join();
            System.out.println("出现了异常，主线程还能执行？");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 启动线程： 线程类的内部首先检测当前线程状态是否为0，如果不是，抛出异常，
     * 表明线程的start方法只能在状态不为0的情况下调用，然后将线程添加到内部线程组中等待执行
     * 然后调用本地方法尝试启动线程，将线程是否启动标示设置为true
     */
    //        public synchronized void start() {
    //        /**
    //         * This method is not invoked for the main method thread or "system"
    //         * group threads created/set up by the VM. Any new functionality added
    //         * to this method in the future may have to also be added to the VM.
    //         *
    //         * A zero status value corresponds to state "NEW".
    //         */
    //        if (threadStatus != 0)
    //            throw new IllegalThreadStateException();
    //
    //        /* Notify the group that this thread is about to be started
    //         * so that it can be added to the group's list of threads
    //         * and the group's unstarted count can be decremented. */
    //        group.add(this);
    //
    //        boolean started = false;
    //        try {
    //            start0();
    //            started = true;
    //        } finally {
    //            try {
    //                if (!started) {
    //                    group.threadStartFailed(this);
    //                }
    //            } catch (Throwable ignore) {
    //                /* do nothing. If start0 threw a Throwable then
    //                  it will be passed up the call stack */
    //            }
    //        }
    //    }


    //    private native void start0();

    /**
     * 实现Runnable接口，通过runnable类的变量来初始化线程
     */
    private static class RunThread implements Runnable {

        @Override
        public void run() {
            int start = 1;
            while (start <= 100) {
                System.out.println(String.format("实现Runnable接口：第%s次调用！", start));
                start ++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

}
