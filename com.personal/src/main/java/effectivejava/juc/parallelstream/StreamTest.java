package effectivejava.juc.parallelstream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-27 07:10:54
 * @author: wanglong16@meicai.cn
 */
public class StreamTest {


    public static void main(String[] args) {
        //parallelStream();
        streamParallelTest();
    }

    private static void streamTest() {
        Stream.of(1,2,3,4,5,6,7,8,9)
                .reduce((a, b) -> {
                    System.out.println(String.format("%s : %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                }).ifPresent(System.out::println);
    }

    /**
     * main : 6 + 7 = 13
     * ForkJoinPool.commonPool-worker-4 : 1 + 2 = 3
     * ForkJoinPool.commonPool-worker-6 : 9 + 10 = 19
     * ForkJoinPool.commonPool-worker-1 : 4 + 5 = 9
     * ForkJoinPool.commonPool-worker-1 : 3 + 9 = 12
     * ForkJoinPool.commonPool-worker-6 : 8 + 19 = 27
     * ForkJoinPool.commonPool-worker-1 : 3 + 12 = 15
     * ForkJoinPool.commonPool-worker-6 : 13 + 27 = 40
     * ForkJoinPool.commonPool-worker-6 : 15 + 40 = 55
     * 55
     * 内部使用fork - join 模型的并行框架
     */
    private static void streamParallelTest() {
        Stream.of(1,2,3,4,5,6,7,8,9,10)
                .parallel()
                .reduce((a, b) -> {
                    System.out.println(String.format("%s : %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                }).ifPresent(System.out::println);
    }

    /**
     * 本文一直在强调的“多核”的情况。其实可以看到，我的本地电脑有8核，但并行计算耗时并不是单线程计算耗时除以8，因为线程的创建、
     * 销毁以及维护线程上下文的切换等等都有一定的开销。所以如果你的服务器并不是多核服务器，那也没必要用Stream的并行计算。
     * 因为在单核的情况下，往往Stream的串行计算比并行计算更快，因为它不需要线程切换的开销
     */
    private static void parallelStream() {
        List<Long> arr = new ArrayList<>();
        for (long i = 1; i <= 50000000; i++) {
            arr.add(i);
        }
        System.out.println(String.format("processors %s", Runtime.getRuntime().availableProcessors()));
        long st = System.currentTimeMillis();
        arr.stream().reduce(Long::sum).ifPresent(System.out::println);
        long stp = System.currentTimeMillis();
        System.out.println(String.format("not parallel cost %s", stp - st));
        arr.stream().parallel().reduce(Long::sum).ifPresent(System.out::println);
        System.out.println(String.format("parallel cost %s", System.currentTimeMillis() - stp));
    }

    /**
     * processors 8
     * 1250000025000000
     * not parallel cost 1089
     * 1250000025000000
     * parallel cost 269
     */

}
