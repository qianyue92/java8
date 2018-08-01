package test;

import dao.Pirate;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
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
     * 中间操作:
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 limit、 skip
     */
    @Test
    public void test1() {
        pirates.stream().distinct().filter(e -> e.getAge() < 30).sorted(Comparator.comparing(Pirate::getReward))
                .limit(3).skip(1).forEach(System.out::println);
    }

    /**
     * 终止操作:
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

    /**
     * 自己生成流,无限流
     * Stream.generate
     * 通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的
     * Stream。把 Supplier 实例传递给 Stream.generate() 生成的 Stream，默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。
     * 由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小。
     */
    @Test
    public void test3() {
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
    }

    /**
     * Stream.iterate
     * iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推。
     * 例：生成一个等差数列
     */
    @Test
    public void test4() {
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
        // 输出：0 3 6 9 12 15 18 21 24 27
    }


}
