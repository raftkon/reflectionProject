import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * IOScript
 */
public class IOScript {

    public static List<String> readFile(String filename) {
        List<String> strArr = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename); // Anoigei arxeio
            BufferedReader br = new BufferedReader(fr); // Diavazei arxeio
            String str = br.readLine();

            while (str != null) {
                strArr.add(str);
                str = br.readLine();
            }
            br.close();
            return strArr;

        } catch (Exception e) {
            System.out.println("IOScript.readFile Error: " + e);
        }

        return strArr;

    }

    public static void createFile(String filename) {
        try {
            File outputFile = new File(filename);
            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, String toWrite) {
        try {

            PrintWriter myWriter = new PrintWriter(new FileWriter(filename, true));
            myWriter.append(toWrite + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + toWrite);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}