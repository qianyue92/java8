package test;

import dao.Pirate;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    Pirate pirate = new Pirate(107, "艾斯", 1, 100);
    Pirate x = new Pirate(108, null, 2, 200);
    Pirate p = null;

    // 需求:获取人员姓名，人员不存在或者姓名为空则返回"无名"
    @Test
    public void test() {
        if (p != null && p.getName() != null) {
            System.out.println(p.getName());
        } else {
            System.out.println("无名");
        }
    }

    // Optional
    @Test
    public void test1() {
        String name = Optional.ofNullable(pirate).map(Pirate::getName).orElse("无名");
        System.out.println(name);
        //输出："艾斯"
    }

    @Test
    public void test3() {
        String name = Optional.ofNullable(x).map(Pirate::getName).orElse("无名");
        System.out.println(name);
        //输出："无名"
    }

    @Test
    public void test4() {
        String name = Optional.ofNullable(p).map(Pirate::getName).orElse("无名");
        System.out.println(name);
        //输出："无名"
    }

    /* 项目中使用情况
    CategoryEntity entity = categoryService.select(categoryId);
    GoodsVo.setCategoryName(entity.getCategoryName);
    -----------------------------------------------------
    GoodsVo.setCategoryName(Optional.ofNullable(entity).map(CategoryEntity::getCategoryName).orElse(""));*/

}
