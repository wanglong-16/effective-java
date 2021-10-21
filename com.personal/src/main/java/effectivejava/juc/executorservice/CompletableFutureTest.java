package effectivejava.juc.executorservice;

import java.util.concurrent.*;

/**
 * @description: 异步任务的抽象
 * @version: 1.0
 * @date: 2021-02-24 17:50:44
 * @author: wanglong16@meicai.cn
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest test = new CompletableFutureTest();
        test.test();
    }

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public void test() throws ExecutionException, InterruptedException {
        //实现 Future<T>, CompletionStage<T> 泛型接口，

        Integer future = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("stage 1 result" + sum);
            return sum;
        }, executorService).thenApply(sum -> {
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("stage 2 result" + sum);
            return sum;
        }).join();

        System.out.println(future);
        executorService.shutdown();
    }

}
