import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        if (args.length == 3) {

            try {
                List<String> strArray = IOScript.readFile(args[0]);
                if (strArray.size() > Integer.parseInt(args[2])) {

                    List<Class<?>> classArray = UtilFunctions.mapStringArrToClassArr(strArray);
                    // * 1a
                    Map<String, List<String>> declaredFields = ReflectionScript.getDeclaredFieldsArr(classArray);
                    // * 1b
                    Map<String, List<String>> allFields = ReflectionScript.getAllFieldsArr(classArray);
                    // * 2a
                    Map<String, List<String>> declaredMethods = ReflectionScript.getDeclaredMethodsArr(classArray);
                    // * 2b
                    Map<String, List<String>> allMethods = ReflectionScript.getAllMethodsArr(classArray);
                    // * 4
                    Map<String, Set<String>> superTypes = ReflectionScript.getAllSuperTypes(classArray);
                    // * 3
                    Map<String, Integer> subTypesMap = ReflectionScript.getAllSubtypes(superTypes);

                    Map<String, Integer> declFieldsMap = UtilFunctions.mapListToInt(declaredFields);
                    Map<String, Integer> allFieldsMap = UtilFunctions.mapListToInt(allFields);
                    Map<String, Integer> declMethodsMap = UtilFunctions.mapListToInt(declaredMethods);
                    Map<String, Integer> allMethodsMap = UtilFunctions.mapListToInt(allMethods);
                    Map<String, Integer> superTypesMap = UtilFunctions.mapSetToInt(superTypes);

                    Object[] declFieldsObject = UtilFunctions.mapToSortedArray(declFieldsMap);
                    Object[] allFieldsObject = UtilFunctions.mapToSortedArray(allFieldsMap);
                    Object[] declMethodsObject = UtilFunctions.mapToSortedArray(declMethodsMap);
                    Object[] allMethodsObject = UtilFunctions.mapToSortedArray(allMethodsMap);
                    Object[] subTypesObject = UtilFunctions.mapToSortedArray(subTypesMap);
                    Object[] superTypesObject = UtilFunctions.mapToSortedArray(superTypesMap);

                    UtilFunctions.mutateAndWriteArray(declFieldsObject, args[1], Integer.parseInt(args[2]), "1a");
                    UtilFunctions.mutateAndWriteArray(allFieldsObject, args[1], Integer.parseInt(args[2]), "1b");
                    UtilFunctions.mutateAndWriteArray(declMethodsObject, args[1], Integer.parseInt(args[2]), "2a");
                    UtilFunctions.mutateAndWriteArray(allMethodsObject, args[1], Integer.parseInt(args[2]), "2b");
                    UtilFunctions.mutateAndWriteArray(subTypesObject, args[1], Integer.parseInt(args[2]), "3");
                    UtilFunctions.mutateAndWriteArray(superTypesObject, args[1], Integer.parseInt(args[2]), "4");
                } else {
                    System.out.println("N must be smaller than the number of classes. Provide a smaller number");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Provide 3 arguments, the input file, the output file and an integer.");
        }

    }
}
