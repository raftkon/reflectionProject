import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        if (args.length == 3) {

            try {
                List<String> strArray = IOScript.readFile(args[0]);
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
                Map<String, Integer> subTypes = ReflectionScript.getAllSubtypes(superTypes);

                // UtilFunctions.iterateMapList(declaredFields, "declared Fields");
                // UtilFunctions.iterateMapList(allFields, "All fields");
                // UtilFunctions.iterateMapList(declaredMethods, "declared Methods");
                // UtilFunctions.iterateMapList(allMethods, "All Methods");
                // UtilFunctions.iterateMapSet(superTypes, "Super Types");
                // UtilFunctions.iterateMapInt(subTypes, "Sub Types");

                UtilFunctions.sortMapList(declaredFields);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Provide 3 arguments, the input file, the output file and an integer.");
        }

    }
}
