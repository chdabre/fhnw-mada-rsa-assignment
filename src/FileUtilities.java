import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This Class is intended to simplify file load/save operations.
 */
public class FileUtilities {
    /**
     * Save a string to a File with the given name.
     */
    public static void saveStringToFile(String s, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the contents of a File and return it as a String.
     */
    public static String readStringFromFile(String fileName) {
        try {
            byte[] encoded;
            encoded = Files.readAllBytes(Paths.get(fileName));
            return new String(encoded, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
