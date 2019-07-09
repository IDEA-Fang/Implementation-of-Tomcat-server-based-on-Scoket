package aop.version2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/30
 * 22:26
 * #
 */
public class TimeHandler implements InvocationHandler{

    private Object target;

    public TimeHandler(Object o) {
        this.target = o;
    }

    @Override
    public void invoke(Object o, Method m) {
        try {
            m.invoke(target.getClass(),new Object[]{o.getClass()});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
