package effectivejava.generic.pecs.bean;

import effectivejava.generic.pecs.bean.Animal;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-24 15:39:03
 * @author: wanglong16@meicai.cn
 */
public class Bird extends Animal {

    private String name;

    @Override
    public void eat() {
        super.eat();
    }

    public void fly() {
        System.out.println("bird can fly");
    }
}
