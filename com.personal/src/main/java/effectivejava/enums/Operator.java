package effectivejava.enums;

/**
 * @description: 策略枚举类
 * @version: 1.0
 * @date: 2021-06-06 15:31:53
 * @author: wanglong16@meicai.cn
 */
public enum Operator {

    PLUS("+"){
        @Override
        public int operator(int a, int b) {
            return a + b;
        }
    },
    MINUS("-") {
        @Override
        public int operator(int a, int b) {
            return a - b;
        }
    },
    MULTI("*") {
        @Override
        public int operator(int a, int b) {
            return a * b;
        }
    },
    DIVID("/") {
        @Override
        public int operator(int a, int b) {
            return a / b;
        }
    };

    private String desc;

    Operator(String desc) {
        this.desc = desc;
    }

    public abstract int operator(int a, int b);
}
