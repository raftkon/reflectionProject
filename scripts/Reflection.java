import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Reflection {

    public static Map<String, List<String>> getDeclaredFieldsMap(List<Class<?>> classArray) {
        Map<String, List<String>> mapDeclFields = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclFields.put(c.getName(), new ArrayList<String>());
            Field[] declFields = c.getDeclaredFields();
            mapDeclFields = Populate.populateDeclaredFields(declFields, mapDeclFields, c);
        }

        return mapDeclFields;
    }

    public static Map<String, List<String>> getAllFieldsMap(List<Class<?>> classArray) {
        Map<String, List<String>> map = getDeclaredFieldsMap(classArray);
        for (Class<?> c : classArray) {
            Class<?> d = c.getSuperclass();
            while (d != null) {
                Field[] declFields = d.getDeclaredFields();
                map = Populate.populateInheritedFields(declFields, map, c);
                d = d.getSuperclass();
            }
        }

        return map;
    }

    public static Map<String, List<String>> getDeclaredMethodsMap(List<Class<?>> classArray) {

        Map<String, List<String>> mapDeclaredMethods = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclaredMethods.put(c.getName(), new ArrayList<String>());
            Method[] declaredMethods = c.getDeclaredMethods();
            mapDeclaredMethods = Populate.populateMethods(declaredMethods, mapDeclaredMethods, c);
        }

        return mapDeclaredMethods;
    }

    public static Map<String, List<String>> getAllMethodsMap(List<Class<?>> classArray) {
        Map<String, List<String>> map = getDeclaredMethodsMap(classArray);
        for (Class<?> c : classArray) {
            Class<?> d = c.getSuperclass();
            while (d != null) {
                Method[] declMethods = d.getDeclaredMethods();
                map = Populate.populateInheritedMethods(declMethods, map, c);
                d = d.getSuperclass();
            }
        }
        return map;
    }

    public static Map<String, Set<String>> getAllSuperTypesMap(List<Class<?>> classArray) {
        Map<String, Set<String>> map = new HashMap<>();
        for (Class<?> c : classArray) {
            map.put(c.getName(), new HashSet<String>());
            map = Populate.populateSuperTypes(map, c);
        }
        return map;
    }

    public static Map<String, Integer> getAllSubtypesMap(Map<String, Set<String>> superTypes) {
        Map<String, Integer> map = new HashMap<>();
        for (String typename : superTypes.keySet()) {
            int counter = 0;
            for (Set<String> value : superTypes.values()) {
                if (value.contains(typename))
                    counter += 1;
            }
            map.put(typename, counter);
        }
        return map;
    }
}
