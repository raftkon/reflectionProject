import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionScript {
    public static Map<String, List<String>> getDeclaredFieldsFromClassArray(List<Class<?>> classArray) {

        Map<String, List<String>> mapDeclFields = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclFields.put(c.getName(), new ArrayList<String>());
            Field[] declFields = c.getDeclaredFields();
            mapDeclFields = UtilFunctions.populateFields(declFields, mapDeclFields, c);
        }
        return mapDeclFields;
    }

    public static Map<String, List<String>> getAllFieldsFromClassArray(List<Class<?>> classArray) {
        Map<String, List<String>> mapAllFields = new HashMap<>();
        for (Class<?> c : classArray) {
            mapAllFields.put(c.getName(), new ArrayList<String>());
            Class<?> d = c;
            while (d != null) {

                Field[] declFields = d.getDeclaredFields();
                d = d.getSuperclass();
                mapAllFields = UtilFunctions.populateFields(declFields, mapAllFields, c);
            }
        }
        return mapAllFields;
    }

    public static Map<String, List<String>> getDeclaredMethodsFromClassArray(List<Class<?>> classArray) {

        Map<String, List<String>> mapDeclaredMethods = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclaredMethods.put(c.getName(), new ArrayList<String>());
            Method[] declaredMethods = c.getDeclaredMethods();
            mapDeclaredMethods = UtilFunctions.populateMethods(declaredMethods, mapDeclaredMethods, c);
        }
        return mapDeclaredMethods;
    }

    public static Map<String, List<String>> getAllMethodsFromClassArray(List<Class<?>> classArray) {
        Map<String, List<String>> mapAllMethods = new HashMap<>();
        for (Class<?> c : classArray) {
            mapAllMethods.put(c.getName(), new ArrayList<String>());
            Class<?> d = c;
            while (d != null) {
                Method[] declaredMethods = d.getDeclaredMethods();
                d = d.getSuperclass();
                mapAllMethods = UtilFunctions.populateMethods(declaredMethods, mapAllMethods, c);
            }
        }
        return mapAllMethods;
    }

    public static Map<String, List<String>> getAllSuperTypes(List<Class<?>> classArray) {
        Map<String, List<String>> map = new HashMap<>();
        for (Class<?> c : classArray) {
            map.put(c.getName(), new ArrayList<String>());
            map = UtilFunctions.populateSuperTypes(map, c);

        }
        return map;
    }

}
