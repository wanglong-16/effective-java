package effectivejava.juc.executorservice;

import java.util.concurrent.*;

/**
 * @description: 字面意思：可完成的将来 => 也就是异步任务的抽象
 * @version: 1.0
 * @date: 2021-02-24 17:50:44
 * @author: wanglong16@meicai.cn
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFutureTest test = new CompletableFutureTest();
    }

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public void test() {
        //实现 Future<T>, CompletionStage<T> 泛型接口，
        CompletableFuture<Integer> integerFuture = new CompletableFuture<>();


        Future<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "future task";
            }
        });

        //executorService.submit(future);
        String f = null;
        try {
            f = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(f);
    }

}
