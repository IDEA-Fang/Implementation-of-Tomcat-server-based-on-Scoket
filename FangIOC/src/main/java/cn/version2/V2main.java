package cn.version2;

import cn.version2.IOC.IocContext;
import cn.version2.apply.UserController;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/26
 * 20:45
 * #
 */
public class V2main {
    public static void main(String[] args) throws Exception {
        UserController userController = (UserController) IocContext.applicationContext.get(UserController.class);
        userController.getUser();
    }
}
