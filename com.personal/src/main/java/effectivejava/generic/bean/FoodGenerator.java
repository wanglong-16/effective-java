package effectivejava.generic.bean;

/**
 * @description: 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型。
 * @version: 1.0
 * @date: 2021-01-25 21:37:20
 * @author: wanglong16@meicai.cn
 */
public class FoodGenerator<T> implements Generator<T> {

    @Override
    public T next() {
        return null;
    }
}
