package effectivejava.util;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-15 16:55:29
 * @author: wanglong16@meicai.cn
 */
public class BinaryOpt {

    //
    public void optAnd() {
        System.out.println(2 & 1);
    }

    public void optOr() {
        System.out.println(2 | 1);
    }

    public void optXor() {
        System.out.println(2 ^ 1);
    }

    public void optNot() {
        System.out.println(~ 1);
    }

    public static void main(String[] args) {
        BinaryOpt op = new BinaryOpt();
        op.optXor();
    }
}
