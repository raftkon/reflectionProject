import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static void iterateMapList(Map<String, List<String>> map, String header) {
        System.out.println("\n" + header.toUpperCase() + "\n");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }

    public static void iterateMapSet(Map<String, Set<String>> map, String header) {
        System.out.println("\n" + header.toUpperCase() + "\n");
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }

    public static void iterateMapInt(Map<String, Integer> map, String header) {
        System.out.println("\n" + header.toUpperCase() + "\n");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

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
            map = UtilFunctions.populateInterfaces(interfaces, map, c);
            map.get(c.getName()).add(d.getName());
            d = d.getSuperclass();
        }
        return map;
    }

    public static void sortMapList(Map<String, List<String>> map) {
        for (List<String> value : map.values()) {

        }
    }
}
