package test;

import dao.Pirate;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
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
     */





}
