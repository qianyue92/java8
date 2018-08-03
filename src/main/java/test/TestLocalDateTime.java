package test;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TestLocalDateTime {

    // 带时区的时间
    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);
    }

    // DateTimeFormatter:解析和格式化日期时间
    @Test
    public void test2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // localtime->String
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);

        System.out.println(strDate);
        // String->localtime
        LocalDateTime newLdt = ldt.parse(strDate, dtf);
        System.out.println(newLdt);
    }

    //Duration : 用于计算两个“时间”间隔  Period : 用于计算两个“日期”间隔
    @Test
    public void test3() {
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Instant ins2 = Instant.now();

        System.out.println("所耗费时间为：" + Duration.between(ins1, ins2));

        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);

        Period pe = Period.between(ld2, ld1);
        System.out.println(pe);
    }

}
