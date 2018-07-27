package test;

import dao.Pirate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLambda1 {

    List<Pirate> pirates = Arrays.asList(
            new Pirate(101, "路飞", 23, 12000.99),
            new Pirate(102, "索隆", 25, 13000.25),
            new Pirate(103, "甚平", 40, 20010.9),
            new Pirate(104, "乔巴", 15, 8.88),
            new Pirate(105, "娜美", 20, 23333),
            new Pirate(106, "乌索普", 22, 55555)
    );

    // 需求一：获取年龄小于25的人员信息
    public List<Pirate> filterPirateAge(List<Pirate> pirates) {
        List<Pirate> list = new ArrayList<>();
        for (Pirate temp : pirates) {
            if (temp.getAge() < 25) {
                list.add(temp);
            }
        }
        return list;
    }

    // 需求二：获取赏金小于20000的人员信息
    // ...

    @Test
    public void test1() {
        List<Pirate> pirateList = filterPirateAge(this.pirates);
        for (Pirate temp : pirateList) {
            System.out.println(temp);
        }
    }

    // 优化
    public List<Pirate> filterPirate(List<Pirate> pirates, MyPredicate<Pirate> mp) {
        List<Pirate> list = new ArrayList<>();
        for (Pirate temp : pirates) {
            if(mp.test(temp)) {
                list.add(temp);
            }
        }
        return list;
    }

    @Test
    public void test2() {
        List<Pirate> list = filterPirate(pirates, new FilterByAge());
        for (Pirate temp : list) {
            System.out.println(temp);
        }

        System.out.println("-------------------------------------------");

        List<Pirate> list2 = filterPirate(pirates, new FilterByReward());
        for (Pirate temp : list2) {
            System.out.println(temp);
        }
    }


    // Lambda表达式
    @Test
    public void test3() {
        List<Pirate> list = filterPirate(pirates, (e) -> e.getAge() < 25);
        list.forEach(System.out::println);

        System.out.println("----------------------------");

        List<Pirate> list1 = filterPirate(pirates, (e) -> e.getReward() < 200000);
        list1.forEach(System.out::println);
    }

    // Stream API
    @Test
    public void test4() {
        pirates.stream().filter((e) -> e.getAge() < 25).forEach(System.out::println);

        System.out.println("------------------------------");

        pirates.stream().filter((e) -> e.getReward() < 20000).forEach(System.out::println);
    }
}
