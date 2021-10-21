package effectivejava.generic;

import effectivejava.generic.bean.GenericObj;
import effectivejava.generic.pecs.bean.Animal;
import effectivejava.generic.pecs.bean.Bird;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-24 09:34:31
 * @author: wanglong16@meicai.cn
 */
public class GenericTest {

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
     * 总之：泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
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

    /**
     * 泛型的类型参数只能是类类型，不能是简单类型。
     * 不能对确切的泛型类型使用instanceof操作
     */
    public static void genericObjectTest() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        GenericObj<Integer> integerGenericObj = new GenericObj<>(123);
        //GenericObj<int> intGenericObj = new GenericObj<int>(123);
        //传入的实参类型需与泛型的类型参数类型相同
        GenericObj<String> stringGenericObj = new GenericObj<>("aa");

        // 定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，
        // 则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用。
        // 如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型
    }

    /**
     * 重要： [?] 是指定特定类型, 此处’？’是类型实参，而不是类型形参
     */
    public static void printGenericObj(GenericObj<?> nums) {
        System.out.println(nums.getElement());
    }


    class A {
        String a = "a";

        public A() {
        }
    }

    class B extends A {

    }

    class C extends B {

    }

    public void testGenericPECS() {
        List<? super B> list = new ArrayList<>();
        //list.add(new A()); super 规定了写入数据的上界是B， 编译报错
        list.add(new B());
        list.add(new C());
        List<? extends B> ll = Collections.singletonList(new B());
        // C c = ll.get(0); extends 规定了读取的下界为B， 编译报错
        A a = ll.get(0);
        System.out.println(a.a);
    }


    public static void main(String[] args) {
        //genericCollection();
        //printGenericClazzInfo();
        GenericTest gt = new GenericTest();
        gt.testGenericPECS();
      //  printGenericObj(new GenericObj<Number>(125) {});
      //  printGenericObj(new GenericObj<Integer>(123) {});
    }
}
