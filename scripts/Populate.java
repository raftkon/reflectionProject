import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Populate {

    public static Map<String, List<String>> populateDeclaredFields(Field[] fields, Map<String, List<String>> map,
            Class<?> c) {
        for (Field field : fields) {
            map.get(c.getName()).add(field.getName());
        }
        return map;
    }

    public static Map<String, List<String>> populateInheritedFields(Field[] fields, Map<String, List<String>> map,
            Class<?> c) {
        for (Field field : fields) {
            if (!field.toString().contains("private")) {
                map.get(c.getName()).add(field.getName());
            }
        }
        return map;
    }

    public static Map<String, List<String>> populateInheritedMethods(Method[] methods, Map<String, List<String>> map,
            Class<?> c) {
        for (Method method : methods) {
            if (!method.toString().contains("private")) {
                map.get(c.getName()).add(method.getName());
            }
        }
        return map;
    }

    public static Map<String, List<String>> populateMethods(Method[] methods, Map<String, List<String>> map,
            Class<?> c) {
        for (Method method : methods) {
            map.get(c.getName()).add(method.getName());
        }
        return map;
    }

    public static Map<String, Set<String>> populateInterfaces(Class<?>[] interfaces, Map<String, Set<String>> map,
            Class<?> c) {
        if (interfaces != null) {
            for (Class<?> i : interfaces) {
                map.get(c.getName()).add(i.getName());
            }
        }
        return map;
    }

    public static Map<String, Set<String>> populateSuperTypes(Map<String, Set<String>> map, Class<?> c) {
        Class<?> d = c.getSuperclass();
        while (d != null) {
            Class<?>[] interfaces = c.getInterfaces();
            map = populateInterfaces(interfaces, map, c);
            map.get(c.getName()).add(d.getName());
            d = d.getSuperclass();
        }
        return map;
    }
}
