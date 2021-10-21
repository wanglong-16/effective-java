package effectivejava.enums;

/**
 * @description: 使用枚举实现单例模式, 可防止反射攻击
 * @version: 1.0
 * @date: 2021-06-06 15:24:56
 * @author: wanglong16@meicai.cn
 */
public enum SingleTon {

    /**
     * 每个枚举类的实现都是 public static final 类型的
     */
    INSTANCE("instance");

    /**
     * enum 类型的构造函数是私有的
     */
    SingleTon(Object object) {
        this.object = object;
    }

    private final Object object;

    public Object getObject() {
        return object;
    }
}
