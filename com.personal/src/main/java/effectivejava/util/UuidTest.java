package effectivejava.util;

import java.util.UUID;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-09 17:20:39
 * @author: wanglong16@meicai.cn
 */
public class UuidTest {

    //4f31b167-1d5c-4e7d-b4d7-c5ec46de6497
    public String randomUUID() {
        return UUID.randomUUID().toString();
    }


    public static void main(String[] args) {
        UuidTest uuidTest = new UuidTest();
        System.out.println(uuidTest.randomUUID());
    }
}
