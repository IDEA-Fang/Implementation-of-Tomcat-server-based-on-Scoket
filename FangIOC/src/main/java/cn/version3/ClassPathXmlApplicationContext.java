package cn.version3;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
 

public class ClassPathXmlApplicationContext {
	private static String PATH;
	private static String ID;
	private static String CLASS;
	private static String NAME;
	private static String VALUE;
 
	public void init() {
		ID = "id";
		CLASS = "class";
		NAME = "name";
		VALUE = "value";
	}
 
	public ClassPathXmlApplicationContext(String path) {
		init();
		// 获取spring读取文件名称
		PATH = path;
	}
 
	public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		// 1、解析xml
		if (beanId == null || beanId.charAt(0) == '\0') {
			return null;
		}
		SAXReader saxReader = new SAXReader();
		Document read = saxReader.read(this.getClass().getClassLoader().getResource(PATH));
		//获取根节点【beans】
		Element rootElement = read.getRootElement();
		//获得根节点下的所有子节点【bean】
		List<Element> elements = rootElement.elements();
		//遍历子节点
		for (Element element : elements) {
			//找到Id
			String id = element.attributeValue(ID);
			if (!beanId.equals(id)) {
				// 结束本次循环
				continue;
			}
			// 2、使用beanid查找对应xml节点，获取class节点属性
			// 从配置文件中获取bean【class】
			String attClass = element.attributeValue(CLASS);
			// 3、使用java的反射机制初始化类
			Class<?> forName = Class.forName(attClass);
			//反射调用有参函数
			Object newInstance = forName.newInstance();
			// 4、获取属性【properties】
			List<Element> sonEle = element.elements();
			//遍历属性下的name,value
			for (Element el : sonEle) {
				// 获取配置文件属性名称
				String attField = el.attributeValue(NAME);
				String attFieldValue = el.attributeValue(VALUE);
				//获得私有属性
				Field declaredField = forName.getDeclaredField(attField);
				//暴力反射获取私有属性
				declaredField.setAccessible(true);
				//给有参构造函数赋值【value】
				declaredField.set(newInstance, attFieldValue);
			}
			return newInstance;
		}
		return null;
 
	}
 
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("version3/applicationContext.xml");
		UserEntity user = (UserEntity) app.getBean("user2");
		System.out.println(user.toString());
	}
 
}