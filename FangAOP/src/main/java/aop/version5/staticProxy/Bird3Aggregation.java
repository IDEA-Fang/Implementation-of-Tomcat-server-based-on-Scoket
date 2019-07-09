package aop.version5.staticProxy;

import aop.version5.Flyable;

/**
 * @author CreateByQinDaoFang on 2019/05/31 13:53
 * description:
 */
public class Bird3Aggregation implements Flyable {

    private Bird bird;


    public Bird3Aggregation(Bird b) {
        this.bird = b;
    }

    @Override
    public void fly() {
        long startTime = System.currentTimeMillis();

        bird.fly();

        long endTime = System.currentTimeMillis();

        System.out.println("bird2 can fly time +" +(endTime - startTime));
    }
}
