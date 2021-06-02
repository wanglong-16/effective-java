package effectivejava.collection;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-31 16:16:43
 * @author: wanglong16@meicai.cn
 */
public class ColMap {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(5));
    }

}
