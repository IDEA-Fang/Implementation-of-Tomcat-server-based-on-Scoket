package aop.version3;

import net.sf.cglib.proxy.MethodProxy;

    import java.lang.reflect.Method;
    import java.util.List;

    /**
     * 描述 被代理者 对应的代理链
     */
    public class ProxyChain<T> {

        private final Class<T> targetClass; // 目标类
        private final T targetObject;       // 目标对象
        private final Method method;        // 此次被代理的方法（被代理的最小单元为方法）
        private final MethodProxy methodProxy;  // 所属 cgLib ，由 cgLib 提供，最终由它执行原目标类中的方法
        private final Object[] params;      // 此次被代理的方法的参数

        private List<Proxy> proxyList;      // 代理链
        private int index = 0;              // index 指示将要执行的 “增强（或称为‘横切逻辑’）”

        public ProxyChain(Class<T> targetClass, T targetObject, Method method, MethodProxy methodProxy, Object[] params, List<Proxy> proxyList) {
            this.targetClass = targetClass;
            this.targetObject = targetObject;
            this.method = method;
            this.methodProxy = methodProxy;
            this.params = params;

            this.proxyList = proxyList;
        }

        public Class<T> getTargetClass() {
            return targetClass;
        }

        public Method getMethod() {
            return method;
        }

        public MethodProxy getMethodProxy() {
            return methodProxy;
        }

        public Object[] getParams() {
            return params;
        }

        public T getTargetObject() {
            return targetObject;
        }

        /**
         * 配合 doProxy() 以及利用 index 实现 “链式代理”
         */
        public Object doProxyChain() throws Throwable {
            Object result;
            if (this.index >= proxyList.size()) {
                result = methodProxy.invokeSuper(targetObject, params);
                this.index = 0; // TODO 自己添加，为了实现 链式代理 的多次调用
            } else {
                result = proxyList.get(this.index++).doProxy(this);
            }
            return result;
        }
    }