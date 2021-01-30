package effectivejava.annotation;

import java.lang.annotation.*;

/**
 * @description: 注解：Java代码、逻辑的标签。
 * @version: 1.0
 * @date: 2021-01-30 20:19:55
 * @author: wanglong16@meicai.cn
 */
@Target(ElementType.METHOD) // 作用目标：方法、包、类型、变量。。。
@Documented // 把注解信息包含到Java doc中
@Retention(RetentionPolicy.RUNTIME) // 注解有效期：源文件、编译后目标文件、运行时
public @interface PrintMessage {

    String message() default "输出信息。。。";

}
