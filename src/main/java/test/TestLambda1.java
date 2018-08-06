package test;

import dao.Pirate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

        List<Pirate> list1 = filterPirate(pirates, (e) -> e.getReward() < 20000);
        list1.forEach(System.out::println);
    }

    // Stream API
    @Test
    public void test4() {
        pirates.stream().filter((e) -> e.getAge() < 25).forEach(System.out::println);

        System.out.println("------------------------------");

        pirates.stream().filter((e) -> e.getReward() < 20000).forEach(System.out::println);
    }


    // forEach效率测试
    @Test
    public void test5() {
        for( int t = 1; t < 5; t ++) {
            for(int tt = 1; tt <= 4; tt ++) {

                List<Integer> testList = Arrays.asList(new Integer[(int) Math.pow(100,(t))]);
                long t1 = System.currentTimeMillis();
                //1.
                for(int i = 0; i < testList.size(); i ++){
                    Integer b = testList.get(i);
                }
                long t2 = System.currentTimeMillis();
                //2.
                for(Integer i:testList){
                    Integer b = i;
                }
                long t3 = System.currentTimeMillis();
                //3.
                testList.forEach(integer -> {Integer b = integer;});
                long t4 = System.currentTimeMillis();
                //4.
                testList.stream().forEach(integer -> {Integer b = integer;});
                long t5 = System.currentTimeMillis();
                //5.
                testList.parallelStream().forEach(integer -> {Integer b = integer;});
                long t6 = System.currentTimeMillis();
                Integer b;
                //6.
                for(Iterator<Integer> iterator = testList.iterator(); iterator.hasNext(); b = iterator.next());
                long t7 = System.currentTimeMillis();
                System.out.println("x" + tt +": loop size:" + testList.size());
                System.out.println("y" + t + ": classical for loop waste millisecond:"+(t2-t1));
                System.out.println("y" + t + ": classical forEach loop waste millisecond:"+(t3-t2));
                System.out.println("y" + t + ": lambda forEach loop waste millisecond:"+(t4-t3));
                System.out.println("y" + t + ": lambda not parallel stream forEach loop waste millisecond:"+(t5-t4));
                System.out.println("y" + t + ": lambda parallel stream forEach loop waste millisecond:"+(t6-t5));
                System.out.println("y" + t + ": classical iterator loop waste millisecond:"+(t7-t6)+"\n");
            }
        }
    }

}
