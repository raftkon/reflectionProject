import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                String inputFile = args[0];
                String outputFile = args[1];
                int N = Integer.parseInt(args[2]);
                List<String> strArray = IOScript.readFile(inputFile);
                if (strArray.size() > N) {
                    List<Class<?>> classArray = UtilFunctions.mapStringArrToClassArr(strArray);
                    List<Object[]> answersList = ReflectionScript.groupAnswersToList(classArray);
                    UtilFunctions.writeListToFile(answersList, outputFile, N);
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