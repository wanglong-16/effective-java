package effectivejava.generic.bean;

import java.util.Random;

/**
 * @description: 传入泛型实参时
 * 定义一个生产器实现这个接口, 虽然我们只创建了一个泛型接口Generator<T>
 * @version: 1.0
 * @date: 2021-01-25 21:19:36
 * @author: wanglong16@meicai.cn
 */
public class FruitGenerator implements Generator<String> {

    private final String [] fruits = new String []{"apple", "banana", "pear"};

    @Override
    public String next() {
        return fruits[new Random().nextInt(3)];
    }
}
