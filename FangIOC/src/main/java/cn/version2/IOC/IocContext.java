package cn.version2.IOC;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class IocContext {
    public static final Map<Class<?>, Object> applicationContext = new ConcurrentHashMap<Class<?>, Object>();
    static{
        String packageName = "cn.version2.apply";
        try {
            initBean(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void initBean(String packageName) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        addClassByAnnotation(url.getPath(),packageName);
/*        while (urls.hasMoreElements()) {
            addClassByAnnotation(urls.nextElement().getPath(), packageName);
        }*/
        
        //IOC实现， 自定注入
        IocUtil.inject();
    }
    //获取指定包路径下实现 Component主键Bean的实例
    private static void addClassByAnnotation(String filePath, String packageName) {
        try {
            File[] files = getClassFile(filePath);
            if (files != null) {
                for (File f : files) {
                    String fileName = f.getName();
                    if (f.isFile()) {
                        Class<?> clazz = Class.forName(packageName + "." + fileName.substring(0, fileName.lastIndexOf(".")));
                        //判断该类是否实现了注解
                        if(clazz.isAnnotationPresent(Component.class)) {
                            applicationContext.put(clazz, clazz.newInstance());
                        }
                    } else {
                        addClassByAnnotation(f.getPath(), packageName + "." + fileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取该路径下所遇的class文件和目录
    private static File[] getClassFile(String filePath) {
        return new File(filePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
    }
}