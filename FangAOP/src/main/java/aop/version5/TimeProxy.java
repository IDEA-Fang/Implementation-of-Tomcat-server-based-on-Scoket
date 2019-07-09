package aop.version5;

/**
 * @author CreateByQinDaoFang on 2019/05/31 15:05
 * description:
 */
public class TimeProxy implements Flyable {

    private Flyable flyable;

    public TimeProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        long startTime = System.currentTimeMillis();

        flyable.fly();

        long endTime = System.currentTimeMillis();

        System.out.println("TimeProxy can fly time +" +(endTime - startTime));
    }
}
