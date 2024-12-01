import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Commons {
        public static List<String> readFileLines(String filename) {
        try {
            File directory = new File("./");
            String filePath = directory.getAbsolutePath() + "/src/Data/" + filename;
            return Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset() );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
