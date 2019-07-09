package aop.version3;

import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.lang.reflect.Method;

    /**
     * Proxy 的 “模板类”
     */
    public abstract class AspectProxy implements Proxy {

        private final static Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

        @Override
        public <T> Object doProxy(ProxyChain<T> proxyChain) {
            Class<T> targetClass = proxyChain.getTargetClass();
            Method method = proxyChain.getMethod();
            Object[] params = proxyChain.getParams();
            Object result;
            try {
                if (intercept(targetClass, method, params)) {   // 拦截条件
                    before(targetClass, method, params);    // 前置增强
                    result = proxyChain.doProxyChain();     // 此处很重要，用于实现链式代理
                    after(targetClass, method, params);     // 后置增强
                } else {
                    result = proxyChain.doProxyChain();
                }
            } catch (Throwable e) { // 这里处理了 doProxyChain() 抛出的异常
                error(targetClass, method, params);
                LOGGER.error("aspect proxy fail", e);
                throw new RuntimeException(e);
            } finally {
                end();
            }

            return result;
        }
        // 重写此以实现 “拦截条件”
        protected <T> boolean intercept(Class<T> targetClass, Method method, Object[] params) {
            return true;
        }
        // 重写此以实现 “前置增强”
        protected <T> void before(Class<T> targetClass, Method method, Object[] params) {

        }
        // 重写此以实现 “后置增强”
        protected <T> void after(Class<T> targetClass, Method method, Object[] params) {

        }
        // 重写此以实现 “抛出增强”
        protected <T> void error(Class<T> targetClass, Method method, Object[] params) {

        }
        // 重写此以实现 "结束增强"
        protected void end() {

        }

    }