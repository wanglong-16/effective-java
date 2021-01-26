package effectivejava.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-26 21:22:45
 * @author: wanglong16@meicai.cn
 */
public class MyAbstractQueueSynchronizer {

    AbstractQueuedSynchronizer abstractQueuedSynchronizer;

    /**
     * aqs 内部使用先进先出的双端队列，实现一些排队和阻塞的机制
     *
     * 资源的共享模式：
     * 1、独占模式(exclusive) 排他模式，资源一次只能一个线程获取 如 Reentrantlock
     * 2、共享模式（share）同时可以被多个线程获取，具体的资源个数由参数指定 如 semaphore/countdownlatch
     * 
     * AQS的设计是基于模板方法模式的，它有一些方法必须要子类去实现的，它们主要有：
     *
     * isHeldExclusively()：该线程是否正在独占资源。只有用到condition才需要去实现它。
     *
     * tryAcquire(int)：独占方式。尝试获取资源，成功则返回true，失败则返回false。
     *
     * tryRelease(int)：独占方式。尝试释放资源，成功则返回true，失败则返回false。
     *
     * tryAcquireShared(int)：共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
     *
     * tryReleaseShared(int)：共享方式。尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false。
     */

}
