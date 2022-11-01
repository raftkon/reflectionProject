import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

    public static void writeFile(String filename) {
        List<String> strArr = new ArrayList<>();
        try {

            FileWriter writer = new FileWriter(filename);
            for (String string : strArr) {
                writer.write(string);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("IOScript.writeFile Error: " + e);
        }
    }
}