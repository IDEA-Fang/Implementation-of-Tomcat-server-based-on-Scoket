package aop.version5;

import java.lang.reflect.Method;

/**
 * @author CreateByQinDaoFang on 2019/05/31 18:02
 * description:
 */
public class TimeProxyMethod implements Flyable {
    private InvocationHandler handler;

    public TimeProxyMethod(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void fly() {
        try {
            Method method =Flyable.class.getMethod("fly");
            this.handler.invoke(this, method, null);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
