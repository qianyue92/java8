package test;

import dao.Pirate;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream操作步骤
 *
 * 1. 创建Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStreamAPI {

    List<Pirate> pirates = Arrays.asList(
            new Pirate(101, "路飞", 23, 12000.99),
            new Pirate(102, "索隆", 25, 13000.25),
            new Pirate(103, "甚平", 40, 20010.9),
            new Pirate(104, "乔巴", 15, 8.88),
            new Pirate(104, "乔巴", 15, 8.88),
            new Pirate(105, "娜美", 20, 23333),
            new Pirate(106, "乌索普", 22, 55555)
    );

    /**
     * 创建流
     */
    // 1. 由值创建流
    Stream stream = Stream.of("a", "b", "c");
    // 2. 数组创建流
    String [] strArray = new String[] {"a", "b", "c"};
    Stream stream1 = Stream.of(strArray);
    Stream stream2 = Arrays.stream(strArray);
    // 3. Collections
    List<String> list = Arrays.asList(strArray);
    Stream stream3 = list.stream();

    /**
     * 中间操作
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 limit、 skip
     */
    @Test
    public void test1() {
        pirates.stream().distinct().filter((e) -> e.getAge() < 30).sorted(Comparator.comparing(Pirate::getReward))
                .limit(3).skip(1).forEach(System.out::println);
    }

    /**
     * 终止操作
     * forEach、toArray、 reduce、 collect、 min、 max、 count
     * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny
     */

    // 1.多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！
    // 而在终止操作时一次性全部处理，称为“惰性求值”。
     @Test
    public void test2() {
         System.out.println(pirates.stream().filter((e) -> e.getAge() < 30));
         System.out.println(pirates.stream().filter((e) -> e.getAge() < 50).findFirst().get());
         // 输出结果:
         // java.util.stream.ReferencePipeline$2@606d8acf
         // Pirate(id=101, name=路飞, age=23, reward=12000.99)
     }

    // 2.对于终止操作，执行后Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次终止运算。下面的代码是错误的：
    /*Stream.of("one", "two", "three", "four")
            .filter(e -> e.length() > 3)
            .forEach(System.out::println)
            .collect(Collectors.toList());*/
}
