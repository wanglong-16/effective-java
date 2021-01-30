package effectivejava.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-30 20:33:43
 * @author: wanglong16@meicai.cn
 */
@Retention(RetentionPolicy.SOURCE)
@Repeatable(Persons.class) // 指定容器注解，表示这个注解是可以重复的 since 1.8
public @interface Person {
    String rule() default "RD";
}
