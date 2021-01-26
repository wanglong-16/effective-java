/**
 * @description: java 泛形
 * 泛形擦除、泛形类，泛形方法、泛形接口
 * 泛型是提供给javac编译器使用的，它用于限定集合的输入类型，让编译器在源代码级别上，
 * 即挡住向集合中插入非法数据。但编译器编译完带有泛形的java程序后，生成的class文件中将不再带有泛形信息，
 * 以此使程序运行效率不受到影响，这个过程称之为“擦除”。
 * 泛形上下限：
 * 在泛型的上限和下限中有一个原则：PECS(Producer Extends Consumer Super)
 * 1、带有子类限定的可以从泛型读取【也就是--->(? extend T)】-------->Producer Extends
 * 2、带有超类限定的可以从泛型写入【也就是--->(? super T)】-------->Consumer Super
 * @version: 1.0
 * @date: 2021-01-24 09:13:15
 * @author: wanglong16@meicai.cn
 */
package effectivejava.generic;