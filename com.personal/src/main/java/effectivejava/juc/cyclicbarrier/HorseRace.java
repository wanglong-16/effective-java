package effectivejava.juc.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-28 15:51:12
 * @author: wanglong16@meicai.cn
 */
public class HorseRace implements Runnable {

    private static final int FINISH_LINE = 75;
    private static final List<Horse> horses = new ArrayList<>();
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    @Override
    public void run() {
        StringBuilder s = new StringBuilder();
        //打印赛道边界
        for (int i = 0; i < FINISH_LINE; i++) {
            s.append("=");
        }
        System.out.println(s);
        //打印赛马轨迹
        for (Horse horse : horses) {
            System.out.println(horse.tracks());
        }
        //判断是否结束
        for (Horse horse : horses) {
            if (horse.getStrides() >= FINISH_LINE) {
                System.out.println(horse + "won!");
                exec.shutdownNow();
                return;
            }
        }
        //休息指定时间再到下一轮
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("barrier-action sleep interrupted");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, new HorseRace());
        for (int i = 0; i < 7; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

}
