package effectivejava.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-24 09:34:31
 * @author: wanglong16@meicai.cn
 */
public class GenericObjTest {

    /**
     * 泛指：类型不确定，可以是任何类型，泛指一切类型都可以被正确执行，类型也作为一种参数传递
     * 特指：在传统的方法中，类，接口中参数传递类型定义，初始化都指定和特定的类型，例如 Ins a、CLazz b ...
     */
    private static void genericCollection() {
        List list = new ArrayList();
        list.add("aaa");
        list.add(111);
        // java 的 collection 相关接口均为泛形容器接口，
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    /**
     * Java 泛型只在编译阶段有效，在编译通过后所有的泛形参数信息都会被去掉，只保留基本数据类型
     * 和 Object 类型，
     * 原因：对象中的属性和嵌套类型向下转形后最终都是基本类型。
     */
    public static void printGenericClazzInfo() {
        List<String> stringGenericList = new ArrayList<>();
        List<Integer> integerGenericList = new ArrayList<>();
        System.out.println(stringGenericList.getClass());
        System.out.println(integerGenericList.getClass());
        //Condition 'stringGenericList.getClass() == integerGenericList.getClass()' is always 'true
        if (stringGenericList.getClass() == integerGenericList.getClass()) {
            System.out.println("类型相同");
        }
    }

    public static void main(String[] args) {
        //genericCollection();
        printGenericClazzInfo();
    }
}
