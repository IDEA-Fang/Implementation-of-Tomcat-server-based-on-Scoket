package aop.version5;

import aop.version5.staticProxy.Bird;

import java.lang.reflect.Constructor;

/**
 * @author CreateByQinDaoFang on 2019/05/31 14:02
 * description:
 */
public class V5MainTest {

    //聚合测试
    public static void main(String[] args) throws Exception {
        Flyable fly = new BirdTimeProxy(new Bird());
        fly.fly();
        System.out.println("---------------");


        Bird bird = new Bird();
        BirdLogProxy p1 = new BirdLogProxy(bird);
        BirdTimeProxy p2 = new BirdTimeProxy(p1);

        p2.fly();

        Object o = Proxy.newProxyInstance();
        System.out.println("---------------");

        MyInvocationHandler handler = new MyInvocationHandler(new Bird());
        Flyable proxy = (Flyable) Proxy.newProxyInstance(Flyable.class, handler);
        proxy.fly();

    }
}
