/*
package aop.version3;

import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import top.inotwant.annocation.Aspect;
    import top.inotwant.proxy.AspectProxy;
    import top.inotwant.proxy.Proxy;
    import top.inotwant.proxy.ProxyManager;
    import top.inotwant.proxy.TransactionProxy;

    import java.lang.annotation.Annotation;
    import java.util.*;

*
     * AOP 实现类


    public final class AopHelper {

        private final static Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

        static {
            LOGGER.warn("======================AOP HELPER=========================");

            Map<Class<?>, List<Proxy>> proxyMap;
            try {
                proxyMap = getProxyMap();
                for (Map.Entry<Class<?>, List<Proxy>> entry : proxyMap.entrySet()) {
                    Class<?> sourceClass = entry.getKey();
                    List<Proxy> proxyList = entry.getValue();
                    Object proxyInstance = ProxyManager.getProxyInstance(sourceClass, proxyList);
                    // 使用 “代理对象” 替换 “被代理对象”
                    BeanHelper.putBean(sourceClass, proxyInstance);
                }
            } catch (Exception e) {
                LOGGER.error("aop helper fail", e);
                throw new RuntimeException(e);
            }
        }

*
         * 生成 (被代理类,代理类集（或称为代理链）) 映射


        public static Map<Class<?>, List<Proxy>> getProxyMap() throws Exception {
            // 用于存储 （目标类，代理链），即返回结果
            Map<Class<?>, List<Proxy>> result = new HashMap<>();
            // 获取 AspectProxy 的所有子类
            Set<Class<?>> proxySet = ClassHelper.getSubClassSet(AspectProxy.class);
            for (Class<?> proxyClass : proxySet) {
                // 判断子类是否被 Aspect 修饰，若被修饰说明它是一个 代理类
                if (proxyClass.isAnnotationPresent(Aspect.class)) {
                    Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                    // 获取该代理类对应注解标识（下面将使用该标识获取所有的目标类（或称为被代理类））
                    Class<? extends Annotation> value = aspect.value();
                    if (!value.equals(Aspect.class)) {
                        // 获取代理类对应的所有目标类
                        Set<Class<?>> annotationClassSet = ClassHelper.getAnnotationClassSet(value);
                        // 通过遍历代理类集合，不断生成 （目标类，代理链）映射
                        for (Class<?> sourceClass : annotationClassSet) {
                            if (result.get(sourceClass) == null) {
                                List<Proxy> proxyList = new ArrayList<>();
                                proxyList.add((Proxy) proxyClass.newInstance());
                                result.put(sourceClass, proxyList);
                            } else {
                                result.get(sourceClass).add((Proxy) proxyClass.newInstance());
                            }
                        }
                    }
                }
            }
            return result;
        }
    }
*/
