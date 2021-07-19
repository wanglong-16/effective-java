package effectivejava.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-28 11:33:26
 * @author: wanglong16@meicai.cn
 */
public class CdlTest {

    public void multiThreadRun(int n) {
        CountDownLatch countDownLatch = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(String.format("current thread [%s] start", Thread.currentThread().getName()));
                try {
                    Thread.sleep(1000 + finalI * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("current thread [%s] -- all threads executed ", Thread.currentThread().getName()));
            }, "cdl-" + i).start();
        }
    }

    public static void main(String[] args) {
        CdlTest cdlTest = new CdlTest();
        cdlTest.multiThreadRun(5);
    }

}
