package effectivejava.java8;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-17 11:39:34
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        showCar(() -> System.out.println("------"));
        Thread t = new Thread();
        System.lineSeparator();
    }

    static void showCar(ICar car) {
        car.display();
    }

}
