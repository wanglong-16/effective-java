package effectivejava.enums;

/**
 * @description: 使用枚举实现单例模式
 * @version: 1.0
 * @date: 2021-06-06 15:24:56
 * @author: wanglong16@meicai.cn
 */
public enum SingleTon {

    INSTANCE("instance");

    private SingleTon(Object object) {
        this.object = object;
    }

    private Object object;

    public Object getObject() {
        return object;
    }
}
