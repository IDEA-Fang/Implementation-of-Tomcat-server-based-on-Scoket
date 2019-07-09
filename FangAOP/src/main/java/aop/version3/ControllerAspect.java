package aop.version3;// ControllerAspect.java

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

/**
     * 拦截 controller 的所有方法
     */

    //@Aspect(Controller.class)
    public class ControllerAspect extends AspectProxy {

        private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
        private long beginTime;

        // 前置增强
        @Override
        protected <T> void before(Class<T> targetClass, Method method, Object[] params) {
            LOGGER.debug("------ begin ------");
            LOGGER.debug(String.format("class :: %s", targetClass.getName()));
            LOGGER.debug(String.format("method :: %s", method.getName()));
            beginTime = System.currentTimeMillis();
        }

        // 后置增强
        @Override
        protected <T> void after(Class<T> targetClass, Method method, Object[] params) {
            LOGGER.debug(String.format("time :: %dms", System.currentTimeMillis() - beginTime));
            LOGGER.debug("------  end  ------");
        }
    }