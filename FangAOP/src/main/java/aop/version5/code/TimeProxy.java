package aop.version5.code;

import aop.version5.Flyable;
import java.lang.Override;
import java.lang.System;

class TimeProxy implements Flyable {
  private Flyable flyable;

  public TimeProxy() {
  }

  public TimeProxy(Flyable fly) {
    this.flyable = fly;
  }

  @Override
  public void fly() {
    long startTime = System.currentTimeMillis();

    this.flyable.fly();

    long endTime = System.currentTimeMillis();

    System.out.println(" proxy newProxyInstance Fly Time =" + (endTime - startTime));
  }
}
