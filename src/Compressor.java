import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Compressor {
    private static Map<String, Integer> buildCompressionMap() {
        Map<String, Integer> initialMap = new HashMap<String, Integer>();
        for (int i = 0; i < 256; i++) {
            initialMap.put(Character.toString((char) i), i);
        }
        return initialMap;
    }

    private static Map<Integer, String> buildDecompressionMap() {
        Map<Integer, String> initialMap = new HashMap<Integer, String>();
        for (int i = 0; i < 256; i++) {
            initialMap.put(i, Character.toString((char) i));
        }
        return initialMap;
    }

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

    public static ArrayList<Integer> compress(String filePath) {
        // Implementation of LZW compression algorithm
        ArrayList<Integer> compressedData = new ArrayList<>();
        String fileContent = readFile(filePath);
        Map<String, Integer> compressionMap = buildCompressionMap();

        String tmp = "";
        // Find the longest string W in the dictionary that matches the current input.
        for (char c : fileContent.toCharArray()) {
            String currStr = tmp + c;
            if (compressionMap.containsKey(currStr)) {
                tmp = currStr;
            } else {
                compressedData.add(compressionMap.get(tmp));                
                compressionMap.put(currStr, compressionMap.size());
                tmp = Character.toString(c);
            }
        }

        if (tmp != null) {
            compressedData.add(compressionMap.get(tmp));
        }

        return compressedData;
    }

    public static String decompress(String compressdedFilePath) {
        String decompressedData = "";
        String compressedFileContent = readFile(compressdedFilePath);
        Map<Integer, String> decompressionMap = buildDecompressionMap();

        ArrayList<String> compressedData = new ArrayList<String>(Arrays.asList(compressedFileContent.split(",")));

        String tmp = compressedData.get(0);
        compressedData.remove(0);

        decompressedData += tmp;

        for (String numberStr : compressedData) {

            String entry = "";
            Integer idx = Integer.parseInt(numberStr);

            if (decompressionMap.containsKey(idx)) {
                entry = decompressionMap.get(idx);
            } else if (decompressionMap.containsKey(idx)) {
                entry = tmp + tmp.charAt(0);
            }

            decompressedData += entry;

            // new sequence; add it to the dictionary
            decompressionMap.put(decompressionMap.size(), tmp + entry.charAt(0));
        }
        
        return decompressedData;
    }
}
