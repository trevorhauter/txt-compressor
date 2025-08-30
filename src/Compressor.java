import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Compressor {
    private static String readFile(String filePath) {
        String fileContext = "";
        try {
            File textFile = new File(filePath);
            Scanner fileReader = new Scanner(textFile);
            while (fileReader.hasNextLine()) {
                fileContext += fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        return fileContext;
    }

    public static void compress(String filePath) {
        String fileContext = readFile(filePath);
        System.out.println(fileContext);
    }
}
