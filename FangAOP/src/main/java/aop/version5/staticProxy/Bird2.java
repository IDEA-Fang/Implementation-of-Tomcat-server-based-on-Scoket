package aop.version5.staticProxy;

import aop.version5.Flyable;

/**
 * @author CreateByQinDaoFang on 2019/05/31 13:50
 * description:
 */
public class Bird2 extends Bird {
    @Override
    public void fly() {
        long startTime = System.currentTimeMillis();

        super.fly();

        long endTime = System.currentTimeMillis();

        System.out.println("bird2 can fly time +" +(endTime - startTime));

    }
}
