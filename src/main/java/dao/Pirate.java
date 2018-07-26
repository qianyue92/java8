package dao;

import lombok.Data;
@Data
public class Pirate {

    // 编码
    private Integer id;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 赏金
    private double reward;

    public Pirate(int id, String name, int age, double reward) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.reward = reward;
    }

    public Pirate() {

    }
}
