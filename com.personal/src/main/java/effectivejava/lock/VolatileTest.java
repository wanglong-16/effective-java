package effectivejava.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-15 14:31:29
 * @author: wanglong16@meicai.cn
 */
public class VolatileTest {

    static class Worker {

        //execute result
        /**
         * t2working ...
         * t2working ...
         * t2working ...
         * t2working ...
         * t2working ...
         * t4shutdown resource
         * t1working ...
         * t3working ...
         */
        //private boolean shutDown = false;

        //private volatile boolean shutDown = false;

        /**
         * t3working ...
         * t3working ...
         * t3working ...
         * t3working ...
         * t4shutdown resource
         * t2working ...
         * t1working ...
         */

        private Semaphore shutDown = new Semaphore(1);

        public void shutDown() {
            try {
                this.shutDown.acquire(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "shutdown resource");
        }

        public void doWork() {
            while (this.shutDown.availablePermits() > 0) {
                System.out.println(Thread.currentThread().getName() + "working ... ");
            }
        }

    }

    public static void main(String[] args) {
        // 资源类
        ShareData shareData = new ShareData();

        // 子线程 实现了Runnable接口的，lambda表达式
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改number的值
            shareData.incr();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + shareData.getNum());

        }, "子线程").start();

        while (shareData.getNum() == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
           // System.out.println("主线程读取到" + shareData.getNum());
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明子线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t 主线程感知到了 number 不等于 0");

        /**
         * 最后输出结果：
         * 子线程     come in
         * 子线程     update number value:100
         * 最后线程没有停止，并行没有输出"主线程知道了 number 不等于0"这句话，说明没有用volatile修饰的变量，变量的更新是不可见的
         */
    }

    /**
     * MESI 缓存一致性协议 标识了多个线程中缓存数据的四种状态
     *
     * M: 被修改（Modified)
     *
     * 该缓存行只被缓存在该CPU的缓存中，并且是被修改过的（dirty),即与主存中的数据不一致，该缓存行中的内存需要在未来的某个时间点（允许其它CPU读取请主存中相应内存之前）写回（write back）主存。
     *
     * 当被写回主存之后，该缓存行的状态会变成独享（exclusive)状态。
     *
     * E: 独享的（Exclusive)
     *
     * 该缓存行只被缓存在该CPU的缓存中，它是未被修改过的（clean)，与主存中数据一致。该状态可以在任何时刻当有其它CPU读取该内存时变成共享状态（shared)。
     *
     * 同样地，当CPU修改该缓存行中内容时，该状态可以变成Modified状态。
     *
     * S: 共享的（Shared)
     *
     * 该状态意味着该缓存行可能被多个CPU缓存，并且各个缓存中的数据与主存数据一致（clean)，当有一个CPU修改该缓存行中，其它CPU中该缓存行可以被作废（变成无效状态（Invalid））。
     *
     * I: 无效的（Invalid）
     *
     * 该缓存是无效的（可能有其它CPU修改了该缓存行）。
     */


}

class ShareData {

    private volatile int num = 0;

    public void incr() {
        num++;
    }

    public int getNum() {
        return num;
    }

}


