package cn.version1.IOC;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/26
 * 17:49
 * #
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    /**
     * ioc的bean
     */
    private Map beanDefinitionMap = new ConcurrentHashMap();

    /**
     * 文件
     */
    private File file;


    public ClassPathXmlApplicationContext(String xmlNamePath) throws Exception {

        //获取路径
        URL url = Thread.currentThread().getContextClassLoader().getResource(xmlNamePath);
        //获取文件
        file = new File(url.toURI());
        //解析
        this.registerbeanDefinitions(file);
    }

    /**
     * @date:   2019/5/26 18:03
     * @author: QinDaoFang
     * @version:version
     * @return: void
     * @param   file
     * @Desc:   desc 解析文件 基于类路径加载配置文件
     */
    public void registerbeanDefinitions(File file) throws IOException, JDOMException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        SAXBuilder saxBuilder = new SAXBuilder();
        //获取文档树
        Document document = saxBuilder.build(file);

        XPathFactory xPathFactory = XPathFactory.instance();
        XPathExpression beanExpression = xPathFactory.compile("//bean");

        //获取bean的节点
        List beanDocument = beanExpression.evaluate(document);
        Iterator beanIterator = beanDocument.iterator();
        //遍历所有的bean节点
        while(beanIterator.hasNext()){
            Element bean = (Element) beanIterator.next();
            //获取文件的id的属性
            String id = bean.getAttributeValue("id");
            String className = bean.getAttributeValue("class");
            //反射获取类的对象，先拿到类的实例对象
            Class clazz = Class.forName(className);
            Object o = clazz.newInstance();
            //获取所有的方法，通过set给属性赋值
            Method[] beanMethods = clazz.getDeclaredMethods();
            //遍历bean下面的节点，反射赋值
            List<Element> propertyList = bean.getChildren("property");
            for (Element propertyElement : propertyList) {
                System.out.println("类-"+o.getClass().getName());
                for (Method beanMethod : beanMethods) {
                    String methodName = beanMethod.getName();
                    System.out.print("类-"+o.getClass().getName()+"+的方法+"+methodName);
                    System.out.println("+name是"+propertyElement.getAttributeValue("name")+"的值-"+propertyElement.getAttributeValue("value"));
                    //属性名
                    String temp = "";
                    //这里读取set方法
                    if (methodName.startsWith("set")){
                        //找到set方法并且转化小写
                        temp = methodName.substring(3).toLowerCase();
                        if (propertyElement.getAttributeValue("name") != null){
                            if (temp.equals(propertyElement.getAttributeValue("name"))){
                                //反射给对象赋值
                                //todo 如果是int类型或者其他类型的转化异常，javabean是int xml当作string，赋值出错
                                Field javaBeanField = clazz.getDeclaredField(temp);
                                Type type = javaBeanField.getGenericType();
                                beanMethod.invoke(o,propertyElement.getAttributeValue("value"));

                            }

                        }else if(propertyElement.getAttributeValue("ref")!=null) {
                            beanMethod.invoke(o,null);
                        }else {
                            //属性为引用对象赋值
                            beanMethod.invoke(o,propertyElement.getAttributeValue("ref"));
                        }
                    }
                }//methods
                beanDefinitionMap.put(id,o);
            }//propertyList

        }



    }


    public Object getBean(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    public void getBean() {

    }
}
