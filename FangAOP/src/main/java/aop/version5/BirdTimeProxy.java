package aop.version5;

import aop.version5.Flyable;

/**
 * @author CreateByQinDaoFang on 2019/05/31 14:10
 * description:
 */
public class BirdTimeProxy  implements Flyable {

    private Flyable fly;

    public BirdTimeProxy (Flyable fly) {
        this.fly = fly;
    }


    @Override
    public void fly() {
        long startTime = System.currentTimeMillis();

        fly.fly();

        long endTime = System.currentTimeMillis();

        System.out.println("BirdTimeProxy Bird3Aggregation2 can fly time +" +(endTime - startTime));
    }
}
