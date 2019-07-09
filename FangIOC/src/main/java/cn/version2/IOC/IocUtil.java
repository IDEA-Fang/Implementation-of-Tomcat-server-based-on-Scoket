package cn.version2.IOC;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;


public class IocUtil {
    public static void inject() {
        Map<Class<?>, Object> map = IocContext.applicationContext;
        try {
            for (Entry<Class<?>, Object> entry : map.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object obj = entry.getValue();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        Class<?> fieldClazz = field.getType();
                        field.setAccessible(true);
                        Object fieldObj = map.get(fieldClazz);
                        field.set(obj, fieldObj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}