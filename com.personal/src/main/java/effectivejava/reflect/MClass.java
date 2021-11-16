package effectivejava.reflect;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-21 19:42:00
 * @author: wanglong16@meicai.cn
 */
public class MClass {

    public void sayS() {
        System.out.println(MClass.this.getClass().getName() + ": ======= ss ");
    }

}
