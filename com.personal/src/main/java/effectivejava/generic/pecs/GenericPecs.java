package effectivejava.generic.pecs;

import effectivejava.generic.pecs.bean.Animal;
import effectivejava.generic.pecs.bean.Bird;
import effectivejava.generic.pecs.bean.Owl;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-24 15:31:57
 * @author: wanglong16@meicai.cn
 */
public class GenericPecs {

    // 𝑓(⋅)是协变（covariant）的，当𝐴≤𝐵时有𝑓(𝐴)≤𝑓(𝐵)成立；
    // 限定数据返回的类型边界
    public Animal pe(Stack<? extends Bird> elements, int index) {
        //Owl o = elements.pop();
        // 读取到的类型的下边界是Bird类型
        return elements.get(index);
    }

    // 𝑓(⋅)是逆变（contravariant）的，当𝐴≤𝐵时有𝑓(𝐵)≤𝑓(𝐴)成立；
    public void cs(Stack<? super Bird> elements) {
        //elements.add(new Animal());
        // 写入数据的上边界是 Bird类型
        elements.add(new Owl());
    }

}
