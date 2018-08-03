package test;

public interface DefaultTest {

    int sub(int a,int b);

    default void defaultMethod() {
        System.out.println("DefalutTest defalut 方法");
    }

    static void staticMethod() {
        System.out.println("DefalutTest static 方法");
    }
}
