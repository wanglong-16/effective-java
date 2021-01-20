package effectivejava.juc.lock;

/**
 * @description: java 关键字 volatile的语义 & 代码实践。
 * @version: 1.0
 * @date: 2021-01-17 10:04:03
 * @author: wanglong16@meicai.cn
 */
public class Volatile {

    //指令重排序、happens-before

    /**
     * 在Java内存模型JMM有一个主内存，每个线程有自己私有的工作内存，工作内存中保存了一些变量在主内存的拷贝。
     *
     * 内存可见性: 指的是线程之间的可见性，当一个线程修改了共享变量时，另一个线程可以读取到这个修改后的值。
     *
     * 重排序： 为优化程序性能，对原有的指令执行顺序进行优化重新排序。重排序可能发生在多个阶段，比如编译重排序、CPU重排序等。
     *
     * happens-before: 是一个给程序员使用的规则，只要程序员在写代码的时候遵循happens-before规则，JVM就能保证指令在多线程之间的顺序性符合程序员的预期。
     *
     * 在Java中，volatile关键字有特殊的内存语义。volatile主要有以下两个功能：
     * 保证变量的内存可见性
     * 禁止volatile变量与普通变量重排序（JSR133提出，Java 5 开始才有这个“增强的volatile内存语义”）
     *
     * 从volatile的内存语义上来看，volatile可以保证内存可见性且禁止重排序。
     *
     * 在保证内存可见性这一点上，volatile有着与锁相同的内存语义，所以可以作为一个“轻量级”的锁来使用。
     * 但由于volatile仅仅保证对单个volatile变量的读/写具有原子性，而锁可以保证整个临界区代码的执行具有原子性。
     * 所以在功能上，锁比volatile更强大；在性能上，volatile更有优势。
     *
     * 在禁止重排序这一点上，volatile也是非常有用的。比如我们熟悉的单例模式，
     * 其中有一种实现方式是“双重锁检查”，防止返回一个未初始化完成的instance！
     */

    private int signal = 0;

    private volatile boolean write = false;

    public void write(int t) {
        signal = t;
        write = true; // step 2
        //当一个线程对volatile修饰的变量进行写操作时，
        // JMM会立即把该线程对应的本地内存中的共享变量的值刷新到主内存；
    }

    /**
     * 在这一点上，volatile与锁具有相同的内存效果，
     * volatile变量的写和锁的释放具有相同的内存语义，volatile变量的读和锁的获取具有相同的内存语义。
     */

    public void render() {
        if (write) { //step 3
            System.out.println("signal is " + signal);
            // 当一个线程对volatile修饰的变量进行读操作时，
            // JMM会把立即该线程对应的本地内存置为无效，从主内存中读取共享变量的值。
        }
    }

    /**
     * 而如果flag变量没有用volatile修饰，在step 2，线程A的本地内存里面的变量就不会立即更新到主内存，
     * 那随后线程B也同样不会去主内存拿最新的值，仍然使用线程B本地内存缓存的变量的值a = 0，flag = false。
     */


}
