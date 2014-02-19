import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * User: Mariola
 * Date: 19.02.14
 */
public class FileUtils {

    private static boolean createXmlDir() {
        File dir = new File("xml");

        return dir.mkdir();
    }

    public static void createFiles(List<String> fileNames) {
        if (fileNames == null) return;

        createXmlDir();


        for(String fileName : fileNames) {
            StringBuilder sb = new StringBuilder();
            sb.append("xml")
                .append(File.separator)
                .append(fileName)
                .append(".bean.xml");

            Path path = Paths.get(sb.toString());

            try {
                if (!Files.exists(path)){
                    Files.createFile(path);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
