package effectivejava.generic.bean;


/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-25 20:58:59
 * @author: wanglong16@meicai.cn
 */
public class GenericObj <A>{

    private A element;

    public GenericObj(A element) {
        this.element = element;
    }

    public A getElement() {
        return element;
    }

    public void setElement(A element) {
        this.element = element;
    }

}
