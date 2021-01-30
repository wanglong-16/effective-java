package effectivejava.annotation;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-30 20:47:45
 * @author: wanglong16@meicai.cn
 */
@FuHao(money = 100000000)
public class Father {

    private static Son mySon = new Son();

    public static void main(String[] args) {
        Annotation[] annotations = mySon.getClass().getAnnotations();
        System.out.println(annotations);
    }

    private static class Son {
        //富一代有个身价一亿的富豪爹，自然就是富豪。
    }
}
