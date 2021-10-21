package effectivejava.java8;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-16 17:53:01
 * @author: wanglong16@meicai.cn
 */
public interface ICar {

    static void running() {
        System.out.println("running");
    }

    /**
     * 接口默认实现，解决了广为诟病的实现类必须实现接口的方法，默认都需要搞个空方法体
     */
    default void showPrice() {
        System.out.println("100000 $");
    }

    void display();
}
