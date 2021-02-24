package effectivejava.juc.executorservice;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: java 1.8 提供了
 * @version: 1.0
 * @date: 2021-02-24 17:14:12
 * @author: wanglong16@meicai.cn
 */
public class ExecutorServiceTest {

    ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread("ExecutorService Thread");
        }
    };

    RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        }
    };

    ExecutorService executorService = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(), threadFactory, rejectedExecutionHandler);

    public static void main(String[] args) {
        ExecutorServiceTest test = new ExecutorServiceTest();
        test.testEsServices();
    }

    public void testEsServices() {
        Callable<Integer> callable0 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int total = 0;
                for (int i = 0; i < 100; i++) {
                    total += i << 1;
                }
                return total;
            }
        };
        executorService.submit(callable0);

        Callable<Integer> callable1 = () -> {
            int total = 0;
            for (int i = 0; i < 1000; i++) {
                total += i << 1;
            }
            return total;
        };

        Runnable runnable = () -> {
            int total = 0;
            for (int i = 0; i < 10000; i++) {
                total += i << 1;
            }
        };
        executorService.submit(() -> {
            int total = 0;
            for (int i = 0; i < 10000; i++) {
                total += i << 1;
            }
            System.out.println("runnable -- 10000 --" + total);
        });
        try {
            System.out.println(callable0.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ExecutorService es = new ExecutorService() {
        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            //使用callable提交一个可执行的任务, 结果通过callable 获取
            return null;
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            //使用runnable提交一个可执行的任务，通过参数来获取返回结果
            return null;
        }

        @Override
        public Future<?> submit(Runnable task) {
            //使用runnable提交一个可执行的任务，无返回结果
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            //唤醒所有任务
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            //间隔指定时间后，唤醒所有任务
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            //唤醒任意一个任务
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            //间隔指定时间后，唤醒任意一个任务
            return null;
        }

        @Override
        public void execute(Runnable command) {
            //立刻通过现有资源执行
        }
    };
}
