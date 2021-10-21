package effectivejava.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-17 15:12:49
 * @author: wanglong16@meicai.cn
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Car> serviceLoader = ServiceLoader.load(Car.class);
        Iterator<Car> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            car.display();
        }
    }
}
