package effectivejava.reflect;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-21 19:41:04
 * @author: wanglong16@meicai.cn
 */
public class ReflectObj {

    public static void main(String[] args) {
        try {
            // 通过系统类加载器拿到当前类信息
            Class<MClass> cl = (Class<MClass>) ClassLoader.getSystemClassLoader().loadClass("effectivejava.reflect.MClass");
            MClass mClassInstance = cl.newInstance();
            mClassInstance.sayS();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
