package effectivejava.classloader;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-20 14:56:22
 * @author: wanglong16@meicai.cn
 */
public class MyLauncher {

    public static void main(String[] args) {
        URLClassPath urlClassPath = Launcher.getBootstrapClassPath();
        for (URL url : urlClassPath.getURLs()) {
            System.out.println(url.toString());
        }
        /**
         * execute result:
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/resources.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/rt.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/sunrsasign.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jsse.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jce.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/charsets.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jfr.jar
         * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/classes
         */

        /**
         * 1. bootstrap class loader: 引导类加载器，只能加载java、javax、sun为开头的java自身的类库
         */



        Launcher.getLauncher().getClassLoader();
        /**
         * 2. extension class loader: 扩展类加载器负责加载 java.ext.dirs所指定目录或jre/libb/ext中的类库，
         * 我们自己定义的jar放到这些路径下，就会被扩展类加载器所加载。
         */


        ClassLoader.getSystemClassLoader();
        /**
         * 3. system class loader: 系统类加载器, 系统类加载器负责加载classpath路径下或java.class.path属性下的指定的了类库。
         * 实际上，系统类加载器是程序中的默认加载器，我们平常所编写的绝大不部分类，默认都是由这个加载器所加载的
         */

        /**
         * 4. user defined class loader: 用户自定义类加载器, 可通过继承class loader 或者 url class loader 实现加载类的功能
         */
    }
}
