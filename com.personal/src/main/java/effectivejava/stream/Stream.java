package effectivejava.stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @description: stream 
 * @version: 1.0
 * @date: 2021-07-19 14:28:41
 * @author: wanglong16@meicai.cn
 */
public class Stream {

    Map<String, Integer> map = new HashMap<String, Integer>() {
        {put("aa", 1);}
        {put("bb", 2);}
        {put("cc", 3);}
        {put("dd", 4);}
        {put("ee", 5);}
    };

    List<String> strings = Arrays.asList("aa", "bb", "cc", "dd", "aa", "aa", "bb");

    List<Person> personList = new ArrayList<>();

    {
        this.personList.add(new Person("aa", "BeiJin", 19));
        this.personList.add(new Person("bb", "BeiJin", 33));
        this.personList.add(new Person("cc", "HeBei", 23));
        this.personList.add(new Person("dd", "HeBei", 65));
    }


    /**
     * 对中间的每个数据元素执行函数 f
     * @return
     */
    public int reduce() {
        return new ArrayList<>(this.map.values()).stream().reduce(0, (p, q) ->
                {
                    System.out.println("中间层结果p " + p);
                    System.out.println("当前要加的项q " + q);
                    p += q;
                    System.out.println("累加后的结果 " + p);
                    return p;
                }
        );
    }

    /**
     * groupingBy function
     * paramOne: key definition
     * paramTwo: value definition
     *
     * almost use Collectors.mapping to convert value to other structure;
     * @return
     */

    // summary frequency
    public Map<String, Long> groupingByI() {
        return strings.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }
    // ...
    public Map<String, Set<String>> groupingByII() {
        return personList.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getName, Collectors.toSet())));
    }

    // ...
    public Map<String, Optional<Integer>> groupingByIII() {
        return personList.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getAge, Collectors.reducing(BinaryOperator.maxBy(
                Comparator.comparingInt(a -> a)
        )))));
    }

    // ...

    /**
     * partitioningBy only return Map<Boolean, List<Person>>
     * @return
     */
    public Map<Boolean, List<Person>> partitioningBy() {
        return personList.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 50));
    }

    public static void main(String[] args) {
        Stream stream = new Stream();
        System.out.println(stream.groupingByII());
    }

    class Person {
        private String name;
        private String city;
        private Integer age;

        public Person(String name, String city, Integer age) {
            this.name = name;
            this.city = city;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }


}
