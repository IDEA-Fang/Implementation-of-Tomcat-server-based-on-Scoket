package aop.version5.code;

import aop.version5.Flyable;
import aop.version5.InvocationHandler;
import java.lang.Override;
import java.lang.reflect.Method;

public class TimeProxyByHandler implements Flyable {
  private InvocationHandler handler;

  public TimeProxyByHandler(InvocationHandler handler) {
    this.handler = handler;
  }

  @Override
  public void fly() {
    try {
    	Method method = aop.version5.Flyable.class.getMethod("fly");
    	this.handler.invoke(this, method, null);
    } catch(Exception e) {
    	e.printStackTrace();
    }
  }
}
