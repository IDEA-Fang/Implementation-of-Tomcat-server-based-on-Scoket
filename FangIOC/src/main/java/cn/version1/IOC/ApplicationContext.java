package cn.version1.IOC;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/26
 * 17:47
 * #
 */
public interface ApplicationContext extends BeanFactory {
    Object getBean(String name);
}
