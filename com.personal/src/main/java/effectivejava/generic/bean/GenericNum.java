package effectivejava.generic.bean;

/**
 * @description: 泛形的继承，上下边界
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
}
