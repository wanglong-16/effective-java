package effectivejava.annotation;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-30 20:29:44
 * @author: wanglong16@meicai.cn
 */
public class PrintTest {

    @PrintMessage(message = "你好！")
    public void sayHello() {
    }

    @PrintMessage(message = "吃了吗？")
    public void sayEat() {
    }
}
