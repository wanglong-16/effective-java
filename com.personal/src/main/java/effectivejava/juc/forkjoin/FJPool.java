package effectivejava.juc.forkjoin;


import java.util.concurrent.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-21 10:49:56
 * @author: wanglong16@meicai.cn
 */
public class FJPool {

    public static void main(String[] args) {
        fjpoolWork();
    }

    /**
     *  complete work cost: 53
     */
    private static final ExecutorService fjp = new ForkJoinPool(1);

    /**
     *  complete work cost: 53
     */
    private static final ExecutorService fixPool = Executors.newFixedThreadPool(8);

    private static void fjpoolWork() {
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = new CompletableFuture[100];
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return new Work(finalI).call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }, fjp);
            futures[i - 1] = future;
        }
        CompletableFuture.allOf(futures).join();
        System.out.println(" complete work cost: " + (System.currentTimeMillis() - start) );

    }


    static class Work implements Callable<Integer> {

        private int start = 0;

        public Work(int start) {
            this.start = start;
        }

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = start; i < start + 100; i++) {
                sum += i;
            }
            System.out.println(System.currentTimeMillis() + "===== exec start:" + start + " res:" + sum);
            return sum;
        }
    }
}
