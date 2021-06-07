package effectivejava.enums;

/**
 * @description: 普通枚举类
 * @version: 1.0
 * @date: 2021-06-06 11:00:21
 * @author: wanglong16@meicai.cn
 */
public enum Range {

    LESS_HUNDRED(0, 100, "less than 100"),
    HUNDRED_THOURAND(100, 1000, "more than 100 less than 1000");

    Range(int start, int end, String desc) {
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    private int start;
    private int end;
    private String desc;
}
