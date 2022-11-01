import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        try {
            List<String> strArray = IOScript.readFile(args[0]);
            List<Class<?>> classArray = UtilFunctions.mapStringArrToClassArr(strArray);
            // * 1a
            Map<String, List<String>> declaredFields = ReflectionScript.getDeclaredFieldsFromClassArray(classArray);
            // * 1b
            Map<String, List<String>> allFields = ReflectionScript.getAllFieldsFromClassArray(classArray);
            // * 2a
            Map<String, List<String>> declaredMethods = ReflectionScript.getDeclaredMethodsFromClassArray(classArray);
            // * 2b
            Map<String, List<String>> allMethods = ReflectionScript.getAllMethodsFromClassArray(classArray);
            // * 4
            Map<String, List<String>> superTypes = ReflectionScript.getAllSuperTypes(classArray);

            // UtilFunctions.iterateUsingEntrySet(declaredFields, "declared Fields");
            // UtilFunctions.iterateUsingEntrySet(allFields, "All fields");
            // UtilFunctions.iterateUsingEntrySet(declaredMethods, "declared Methods");
            // UtilFunctions.iterateUsingEntrySet(allMethods, "All Methods");
            UtilFunctions.iterateUsingEntrySet(superTypes, "Super Types");
            System.out.println("----------------------------");
            for (Map.Entry<String, List<String>> entry : superTypes.entrySet()) {
                System.out.println(entry.getValue().stream().reduce("", (total, word) -> word.toUpperCase()));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
