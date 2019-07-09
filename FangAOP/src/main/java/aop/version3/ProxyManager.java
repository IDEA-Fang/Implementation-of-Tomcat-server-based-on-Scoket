package aop.version3;

import net.sf.cglib.proxy.Enhancer;
    import net.sf.cglib.proxy.MethodInterceptor;
    import net.sf.cglib.proxy.MethodProxy;

    import java.lang.reflect.Method;
    import java.util.List;

    public class ProxyManager {

        /**
         * 获取代理对象
         * @param targetClass 目标类
         * @param proxyList 代理链
         * @return 代理对象
         */
        @SuppressWarnings("unchecked")
        public static <T> T getProxyInstance(final Class<T> targetClass, final List<Proxy> proxyList) {
            return (T) Enhancer.create(targetClass, new MethodInterceptor() {
                @Override
                public Object intercept(Object targetObject, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
                    return new ProxyChain<T>(targetClass, (T) targetObject, method, methodProxy, params, proxyList).doProxyChain();
                }
            });
        }
    }