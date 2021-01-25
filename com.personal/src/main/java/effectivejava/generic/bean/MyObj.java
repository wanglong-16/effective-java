package effectivejava.generic.bean;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-25 22:11:38
 * @author: wanglong16@meicai.cn
 */
public class MyObj {

    private String str = "test";

    private int num = 12;

    @Override
    public String toString() {
        return "MyObj{" +
                "test='" + str + '\'' +
                ", num=" + num +
                '}';
    }
}
