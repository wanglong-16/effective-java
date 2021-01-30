package effectivejava.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-30 20:46:35
 * @author: wanglong16@meicai.cn
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Inherited
public @interface FuHao {

    int money() default 10000000;

}
