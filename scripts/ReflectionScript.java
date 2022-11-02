import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionScript {

    public static Map<String, List<String>> getDeclaredFieldsArr(List<Class<?>> classArray) {
        Map<String, List<String>> mapDeclFields = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclFields.put(c.getName(), new ArrayList<String>());
            Field[] declFields = c.getDeclaredFields();
            mapDeclFields = UtilFunctions.populateDeclaredFields(declFields, mapDeclFields, c);
        }
        return mapDeclFields;
    }

    public static Map<String, List<String>> getAllFieldsArr(List<Class<?>> classArray) {
        Map<String, List<String>> map = getDeclaredFieldsArr(classArray);
        for (Class<?> c : classArray) {
            Class<?> d = c.getSuperclass();
            while (d != null) {
                Field[] declFields = d.getDeclaredFields();
                map = UtilFunctions.populateInheritedFields(declFields, map, c);
                d = d.getSuperclass();
            }
        }
        return map;
    }

    public static Map<String, List<String>> getDeclaredMethodsArr(List<Class<?>> classArray) {

        Map<String, List<String>> mapDeclaredMethods = new HashMap<>();
        for (Class<?> c : classArray) {
            mapDeclaredMethods.put(c.getName(), new ArrayList<String>());
            Method[] declaredMethods = c.getDeclaredMethods();
            mapDeclaredMethods = UtilFunctions.populateMethods(declaredMethods, mapDeclaredMethods, c);
        }
        return mapDeclaredMethods;
    }

    public static Map<String, List<String>> getAllMethodsArr(List<Class<?>> classArray) {
        Map<String, List<String>> map = getDeclaredMethodsArr(classArray);
        for (Class<?> c : classArray) {
            Class<?> d = c.getSuperclass();
            while (d != null) {
                Method[] declMethods = d.getDeclaredMethods();
                map = UtilFunctions.populateInheritedMethods(declMethods, map, c);
                d = d.getSuperclass();
            }
        }
        return map;
    }

    public static Map<String, Set<String>> getAllSuperTypes(List<Class<?>> classArray) {
        Map<String, Set<String>> map = new HashMap<>();
        for (Class<?> c : classArray) {
            map.put(c.getName(), new HashSet<String>());
            map = UtilFunctions.populateSuperTypes(map, c);
        }
        return map;
    }

    public static Map<String, Integer> getAllSubtypes(Map<String, Set<String>> superTypes) {
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

    public static List<Object[]> groupAnswersToList(List<Class<?>> classArray) {
        Map<String, Integer> declFieldsMap = UtilFunctions
                .mapListToInt(ReflectionScript.getDeclaredFieldsArr(classArray));
        Map<String, Integer> allFieldsMap = UtilFunctions.mapListToInt(ReflectionScript.getAllFieldsArr(classArray));
        Map<String, Integer> declMethodsMap = UtilFunctions
                .mapListToInt(ReflectionScript.getDeclaredMethodsArr(classArray));
        Map<String, Integer> allMethodsMap = UtilFunctions.mapListToInt(ReflectionScript.getAllMethodsArr(classArray));
        Map<String, Integer> superTypesMap = UtilFunctions.mapSetToInt(ReflectionScript.getAllSuperTypes(classArray));
        Map<String, Integer> subTypesMap = ReflectionScript
                .getAllSubtypes(ReflectionScript.getAllSuperTypes(classArray));

        Object[] declFieldsObject = UtilFunctions.mapToSortedArray(declFieldsMap);
        Object[] allFieldsObject = UtilFunctions.mapToSortedArray(allFieldsMap);
        Object[] declMethodsObject = UtilFunctions.mapToSortedArray(declMethodsMap);
        Object[] allMethodsObject = UtilFunctions.mapToSortedArray(allMethodsMap);
        Object[] subTypesObject = UtilFunctions.mapToSortedArray(subTypesMap);
        Object[] superTypesObject = UtilFunctions.mapToSortedArray(superTypesMap);

        List<Object[]> answers = new ArrayList<>();
        answers.add(declFieldsObject);
        answers.add(allFieldsObject);
        answers.add(declMethodsObject);
        answers.add(allMethodsObject);
        answers.add(subTypesObject);
        answers.add(superTypesObject);
        return answers;
    }
}
