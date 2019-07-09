package aop.version1.a2;

import aop.version1.Hello;
import aop.version1.IHello;

public class DynaTest1 {
    public static void main(String[] args) {
        IHello hello = (IHello) new DynaProxyHello1().bind(new Hello(),new DLogger());//如果我们需要日志功能，则使用代理类
        //IHello hello = new Hello();//如果我们不需要日志功能则使用目标类
        hello.sayHello("明天");
    }
}