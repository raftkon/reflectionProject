import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static Map<String, Integer> mapListToInt(Map<String, List<String>> map) {
        Map<String, Integer> mapWithSizes = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            mapWithSizes.put(entry.getKey(), entry.getValue().size());
        }
        return mapWithSizes;
    }

    public static Map<String, Integer> mapSetToInt(Map<String, Set<String>> map) {
        Map<String, Integer> mapWithSizes = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            mapWithSizes.put(entry.getKey(), entry.getValue().size());
        }
        return mapWithSizes;

    }

    public static Object[] mapToSortedArray(Map<String, Integer> map) {
        Object[] array = map.entrySet().toArray();
        Arrays.sort(array, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        return array;

    }

    public static void mutateAndWriteArray(Object[] object, String filename, int N, String exerNum) {
        String toWrite = "";
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                toWrite += exerNum + ": " + ((Map.Entry<String, Integer>) object[i]).getKey() + ", ";
            } else if (i == N - 1) {

                toWrite += ((Map.Entry<String, Integer>) object[i]).getKey();
            } else {
                toWrite += ((Map.Entry<String, Integer>) object[i]).getKey() + ", ";

            }
        }

        IOScript.createFile(filename);
        IOScript.writeToFile(filename, toWrite);
    }

    public static void writeListToFile(List<Object[]> answersList, String outputFile, int N) {
        UtilFunctions.mutateAndWriteArray(answersList.get(0), outputFile, N, "1a");
        UtilFunctions.mutateAndWriteArray(answersList.get(1), outputFile, N, "1b");
        UtilFunctions.mutateAndWriteArray(answersList.get(2), outputFile, N, "2a");
        UtilFunctions.mutateAndWriteArray(answersList.get(3), outputFile, N, "2b");
        UtilFunctions.mutateAndWriteArray(answersList.get(4), outputFile, N, "3");
        UtilFunctions.mutateAndWriteArray(answersList.get(5), outputFile, N, "4");
    }
}