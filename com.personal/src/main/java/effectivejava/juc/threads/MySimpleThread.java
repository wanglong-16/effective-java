package effectivejava.juc.threads;

/**
 * @description: 多线程基础，thread 类和 runnable 接口
 * @version: 1.0
 * @date: 2021-01-12 06:10:45
 * @author: wanglong16@meicai.cn
 */
public class MySimpleThread {

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


    public static void main(String[] args) {
        ExtThread extThread = new ExtThread();
        //启动线程
        extThread.start();

        new Thread(new RunThread()).start();
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

    /**
     * 为何本地方法start0没有注释说明呢？从命名来看是否可以猜测是启动【执行】当前线程组的第一个线程
     */
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
