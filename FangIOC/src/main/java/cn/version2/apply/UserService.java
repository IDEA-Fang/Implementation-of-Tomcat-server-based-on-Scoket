package cn.version2.apply;

import cn.version2.IOC.Component;

/**
 * 用户Service实现
 * 
 * @author jijs
 * @date 2017-12-3
 */
@Component
public class UserService {
    public User getUser() {
        User user = new User("张三", 20);
        return user;
    }
}