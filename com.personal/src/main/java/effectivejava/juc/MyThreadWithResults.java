package effectivejava.juc;

import java.util.concurrent.*;

/**
 * @description: 带有返回值的线程模型 Callable, Future, FutureTask
 * @version: 1.0
 * @date: 2021-01-12 07:37:54
 * @author: wanglong16@meicai.cn
 */
public class MyThreadWithResults {

    /**
     * 通过实现Callable接口，触发带有返回值的调用, 支持泛形。
     */
    private static class CallThread implements Callable<Long> {

        public Long call() throws Exception {
            long st = System.currentTimeMillis();
            int start = 1;
            while (start <= 100) {
                System.out.println(String.format("实现Callable接口：第%s次调用！", start));
                start ++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            return System.currentTimeMillis() - st;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallThread callThread = new CallThread();
        Future<Long> costMs = executorService.submit(callThread);

        try {
            System.out.println(String.format("Future completed, cost %s ms", costMs.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // or this
        FutureTask<Long> task = new FutureTask<Long>(callThread);
        try {
            task.run();
            if (task.isDone()) {
                System.out.println(String.format("FutureTask completed, cost %s ms", task.get()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Future :期望
     * 封装返回结果的一个线程模型，内置五个抽象方法
     *
     * cancel方法是试图取消一个线程的执行。注意是试图取消，并不一定能取消成功。因为任务可能已完成、已取消、或者一些其它因素不能取消，存在取消失败的可能。boolean类型的返回值是“是否取消成功”的意思。
     * 参数paramBoolean表示是否采用中断的方式取消线程执行。
     * 所以有时候，为了让任务有能够取消的功能，就使用Callable来代替Runnable。如果为了可取消性而使用 Future但又不提供可用的结果，则可以声明 Future<?>形式类型、并返回 null作为底层任务的结果。
     */

    //    boolean cancel(boolean mayInterruptIfRunning);

    //    boolean isCancelled();

    //    boolean isDone();

    //    V get() throws InterruptedException, ExecutionException;

    //    V get(long timeout, TimeUnit unit)
    //        throws InterruptedException, ExecutionException, TimeoutException;

    /**
     * jdk 提供了Future接口的实现类 FutureTask类 来帮助使用
     * 设定了任务执行的7种状态。
     */

    private volatile int state;
    private static final int NEW          = 0;
    private static final int COMPLETING   = 1;
    private static final int NORMAL       = 2;
    private static final int EXCEPTIONAL  = 3;
    private static final int CANCELLED    = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED  = 6;


}
