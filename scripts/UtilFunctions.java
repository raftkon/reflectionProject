import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilFunctions {
    public static List<Class<?>> mapStringArrToClassArr(List<String> classNames) {
        List<Class<?>> classArray = new ArrayList<>();
        if (classNames != null) {
            try {
                for (String className : classNames) {
                    classArray.add(Class.forName(className));
                }
                return classArray;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return classArray;
    }

    public static void iterateUsingEntrySet(Map<String, List<String>> map, String extra) {
        System.out.println("\n" + extra.toUpperCase() + "\n");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }

    public static Map<String, List<String>> populateFields(Field[] fields, Map<String, List<String>> map,
            Class<?> c) {
        for (Field field : fields) {
            map.get(c.getName()).add(field.getName());
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

    public static Map<String, List<String>> populateInterfaces(Class<?>[] interfaces, Map<String, List<String>> map,
            Class<?> c) {
        if (interfaces != null) {
            for (Class<?> i : interfaces) {
                map.get(c.getName()).add(i.getName());
            }
        }
        return map;
    }

    public static Map<String, List<String>> populateSuperTypes(Map<String, List<String>> map, Class<?> c) {
        Class<?> d = c.getSuperclass();
        while (d != null) {
            Class<?>[] interfaces = c.getInterfaces();
            map = UtilFunctions.populateInterfaces(interfaces, map, c);
            map.get(c.getName()).add(d.getName());
            d = d.getSuperclass();
        }
        return map;
    }

}
