package effectivejava.juc;

import effectivejava.juc.lock.MyLock;
/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-12 07:38:49
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        //testWithNoLock();
        testMyLockSync();
    }

    /**
     * 无锁测试
     */
    private static void testWithNoLock() {
        MyLock myLock = new MyLock();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.orderPlus(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.orderPlus(2);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.orderPlus(3);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.orderPlus(4);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        //不同步执行，最终结果是否符合预期
        //999999
    }


    /**
     * 测试锁
     */
    private static void testMyLockSync() {
        MyLock myLock = new MyLock();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.syncOrderPlus(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.syncOrderPlus(2);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.syncOrderPlus(3);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                myLock.syncOrderPlus(4);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        //不同步执行，最终结果是否符合预期
        //1000000
    }
}
