import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the aboslute path of the text file you would like to compress:");
        
        String filePath = input.nextLine();

        input.close();

        ArrayList<Integer> compressedData = Compressor.compress(filePath);


        try {
            FileWriter compressedFileWriter = new FileWriter(filePath + ".lzw"); 
            compressedFileWriter.write(compressedData.toString().replace(" ", ",").replace("[", "").replace("]", "")); 
            compressedFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

    }
}
