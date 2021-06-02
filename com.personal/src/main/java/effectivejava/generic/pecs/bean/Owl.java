package effectivejava.generic.pecs.bean;

import effectivejava.generic.pecs.bean.Bird;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-24 16:03:30
 * @author: wanglong16@meicai.cn
 */
public class Owl extends Bird {

    private String name;

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public void fly() {
        super.fly();
    }
}
