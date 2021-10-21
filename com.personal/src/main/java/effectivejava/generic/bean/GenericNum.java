package effectivejava.generic.bean;

/**
 * @description: 泛形的限定符，类型检查上下边界
 * @version: 1.0
 * @date: 2021-01-25 22:40:00
 * @author: wanglong16@meicai.cn
 */
public class GenericNum <N extends Number>{

    private N num;

    public GenericNum(N num) {
        this.num = num;
    }

    public N getNum() {
        return num;
    }

    public void setNum(N num) {
        this.num = num;
    }

    /**
     * 带有超类限定的可以从泛型写入【也就是--->(? super T)】-------->Consumer Super
     * @param supNum
     */
    private void print(Class<? super Number> supNum) {
        System.out.println(supNum.cast(new Object()));
    }

    /**
     * 带有子类限定的可以从泛型读取【也就是--->(? extend T)】-------->Producer Extends
     * @param extNum
     */
    private void printV1(Class<? extends Number> extNum) {
        System.out.println(extNum.getClass().getName());
    }
}
