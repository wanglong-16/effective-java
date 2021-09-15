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
                //当多个线程到达同步点后统一执行
                System.out.println(String.format("current thread [%s] -- all threads executed ", Thread.currentThread().getName()));
            }, "cdl-" + i).start();
        }
    }


    public void imitateQueryInterFaceAndGetDb() {
        CountDownLatch cdl = new CountDownLatch(2);
        // 查库
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("query from db end");
                cdl.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 查接口
        new Thread(() -> {
            try {
                Thread.sleep(200);
                System.out.println("query from interface end");
                cdl.countDown();
                cdl.await();
                System.out.println(Thread.currentThread().getName() + "executed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    public static void main(String[] args) {
        CdlTest cdlTest = new CdlTest();
        cdlTest.imitateQueryInterFaceAndGetDb();
    }

}
