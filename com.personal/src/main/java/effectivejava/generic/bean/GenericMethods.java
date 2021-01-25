package effectivejava.generic.bean;

/**
 * @description: 泛型方法能使方法独立于类而产生变化，以下是一个基本的指导原则：
 * 无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化， *
 * 那么就应该使用泛型方法。另外对于一个static的方法而已，无法访问泛型类型的参数。
 * 所以如果static方法要使用泛型能力，就必须使其成为泛型方法。
 * @version: 1.0
 * @date: 2021-01-25 22:07:04
 * @author: wanglong16@meicai.cn
 */
public class GenericMethods {

    private static final FoodGenerator foodGenerator = new FoodGenerator<>();

    public GenericMethods() {
    }

    /**
     * 泛型方法，是在调用方法的时候指明泛型的具体类型 。
     */
    public static <T> void printClazz(Class<T> clazz) {
        System.out.println("GenericString" + clazz.toGenericString());
        System.out.println("String" + clazz.toString());
    }

    /**
     * 泛型方法，使用泛形时先声明
     */
    public static <T, V, E, S> void printMutiGeneric(Class<T> clazz, V v, E element, S str) {
        System.out.println("GenericString" + clazz.toGenericString());
        System.out.println("String" + clazz.toString());
        System.out.println(str.toString());
        if (v instanceof Integer && element instanceof Integer) {
            System.out.println((Integer) v + (Integer) element);
        }
    }

    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //?是一种类型实参
    public void showKeyValue2(GenericObj<?> obj){
        System.out.println("elements  " + obj.getElement());
    }

    /**
     * 泛形可变参数方法
     * @param args
     * @param <T>
     */
    public <T> void printT(T... args){
        for(T t : args){
            System.out.println("t is " + t);
        }
    }

    /**
     * 泛形边界方法, 对通配符实参泛形限定
     */
    public static void printNum(GenericObj<? extends Number> num) {
        System.out.println(num);
    }

    /**
     * 泛形边界方法, 对返回值泛形限定
     */
    public static <T extends Generator> T printNumV1() {
        return (T) foodGenerator;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        printClazz(MyObj.class);
        printClazz(Class.forName("effectivejava.generic.bean.MyObj"));
        System.out.println(new MyObj().toString());
    }
}
