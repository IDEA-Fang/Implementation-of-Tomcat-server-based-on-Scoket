package aop.version5.staticProxy;

import aop.version5.Flyable;

import java.util.Random;

/**
 * @author CreateByQinDaoFang on 2019/05/31 13:41
 * description:
 */
public class Bird implements Flyable {
    @Override
    public void fly() {
        long startTime = System.currentTimeMillis();

        System.out.println(" i am fang and can fly---------------->>>>>>>>>>>>>>>>>");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("i am bird can fly time+"+(endTime - startTime));

    }
}
