package effectivejava.annotation;

/**
 * @description: Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。
 * 什么样的注解会多次应用呢？通常是注解的值可以同时取多个。
 * @version: 1.0
 * @date: 2021-01-30 20:33:14
 * @author: wanglong16@meicai.cn
 */
@Person(rule = "PM")
@Person(rule = "TEST")
public class SuperMan {

    private String name;

    public SuperMan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
