package cn.version2.apply;


import cn.version2.IOC.Component;
import cn.version2.IOC.Inject;

/**
 * 用户Controller实现
 * 
 * @author jijs
 * @date 2017-12-3
 */
@Component
public class UserController {
    @Inject
    private UserService userService;
    public void getUser() {
        User user = userService.getUser();
        System.out.println(user);
    }
}