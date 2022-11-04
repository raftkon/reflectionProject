import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utilities {
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

    @SuppressWarnings("unchecked")
    public static Object[] mapToSortedArray(Map<String, Integer> map) {
        Object[] array = map.entrySet().toArray();
        Arrays.sort(array, new Comparator<>() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        return array;

    }

    @SuppressWarnings("unchecked")
    public static void mutateAndWriteList(Object[] object, String filename, int N, String exerNum) {
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
        Utilities.mutateAndWriteList(answersList.get(0), outputFile, N, "1a");
        Utilities.mutateAndWriteList(answersList.get(1), outputFile, N, "1b");
        Utilities.mutateAndWriteList(answersList.get(2), outputFile, N, "2a");
        Utilities.mutateAndWriteList(answersList.get(3), outputFile, N, "2b");
        Utilities.mutateAndWriteList(answersList.get(5), outputFile, N, "3");
        Utilities.mutateAndWriteList(answersList.get(4), outputFile, N, "4");
    }

    public static List<Map<String, Integer>> groupMaps(List<Class<?>> classArray) {
        List<Map<String, Integer>> groupedMaps = new ArrayList<>();
        groupedMaps.add(Utilities
                .mapListToInt(Reflection.getDeclaredFieldsMap(classArray)));
        groupedMaps.add(Utilities.mapListToInt(Reflection.getAllFieldsMap(classArray)));
        groupedMaps.add(Utilities
                .mapListToInt(Reflection.getDeclaredMethodsMap(classArray)));
        groupedMaps.add(Utilities.mapListToInt(Reflection.getAllMethodsMap(classArray)));
        groupedMaps.add(Utilities.mapSetToInt(Reflection.getAllSuperTypesMap(classArray)));
        groupedMaps.add(Reflection
                .getAllSubtypesMap(Reflection.getAllSuperTypesMap(classArray)));
        return groupedMaps;
    }

    public static List<Object[]> groupAnswersToList(List<Class<?>> classArray) {
        List<Object[]> answers = new ArrayList<>();
        List<Map<String, Integer>> groupedMaps = groupMaps(classArray);

        for (Map<String, Integer> map : groupedMaps) {
            answers.add(Utilities.mapToSortedArray(map));

        }
        return answers;
    }

}
