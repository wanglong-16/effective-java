package effectivejava.generic.bean;

/**
 * @description: 泛形容器的接口常用于各种生成器
 * @version: 1.0
 * @date: 2021-01-25 21:18:40
 * @author: wanglong16@meicai.cn
 */
public interface Generator<T> {

    T next();
}
