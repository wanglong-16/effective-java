package effectivejava.java8;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-16 17:54:31
 * @author: wanglong16@meicai.cn
 */
public class BiYaDi implements ICar {

    @Override
    public void showPrice() {
        System.out.println("bi ya da == 10000 $");
    }

    @Override
    public void display() {
        System.out.println("========--====");
    }
}
