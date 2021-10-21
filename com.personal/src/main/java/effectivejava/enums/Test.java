package effectivejava.enums;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-06 10:51:19
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Season.SPRING);
        Object singleInstance = SingleTon.INSTANCE.getObject();
        System.out.println(singleInstance.toString());
        int a = 11, b = 23;
        System.out.println(Operator.PLUS.calculate(10, Operator.MINUS.calculate(a, b)));
    }
}
