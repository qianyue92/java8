package test;

import dao.Pirate;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    Pirate pirate = new Pirate(107, null, 1, 100);

    // 需求:获取人员姓名，为空则返回"新人"
    @Test
    public void test() {
        String name = pirate.getName();
        if (name != null) {
            System.out.println(name);
        } else {
            System.out.println("新人");
        }
    }

    // Optional
    //Entity entity = xxService.select();
    //String name = Optional.ofNullable(entity.getName).orElse("新人");
    @Test
    public void test1() {
        String name = Optional.ofNullable(pirate.getName()).orElse("新人");
        System.out.println(name);
    }

}
