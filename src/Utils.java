import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    /**
     * Returns the contents of a file in the static folder
     *
     * @param fileName The name of the file
     * @return Contents of static/{fileName}
     * @throws IOException If an I/O error occurs reading from the file
     */
    public static String getStaticFile(String fileName) throws IOException {
        Path staticFolder = Paths.get("../static/");
        Path filePath = staticFolder.resolve(fileName);
        List<String> lines = Files.readAllLines(filePath);
        return String.join("\n", lines);
    }
}
