import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the aboslute path of the text file you would like to compress:");
        
        String filePath = input.nextLine();

        Compressor.compress(filePath);

        input.close();
    }
}
