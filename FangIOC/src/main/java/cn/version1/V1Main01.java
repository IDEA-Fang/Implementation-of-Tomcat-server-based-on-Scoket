package cn.version1;

import cn.version1.IOC.ClassPathXmlApplicationContext;
import cn.version1.applyToservice.StudentService;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/26
 * 12:47
 * #
 */
public class V1Main01 {

    /**
     * 	1-编写要解析的xml文件
     * 	2-xml文件解析获取bean节点
     * 	3-反射动态获取xml配置文件设置对象的属性值
     * 	4-程序调用获取bean对象
     * @param args
     */

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("version1/applicationContext.xml");
        StudentService student=(StudentService)context.getBean("StudentService");
        System.out.println(student.getStudent().toString());

    }
}
