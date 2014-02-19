import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Mariola
 * Date: 19.02.14
 */
public class SqlReader {
    private static String fileName = "SQLCustomerLoaderChange.sql";

    public static void createFiles() {
        try {
            Path paths = Paths.get(fileName);
            Scanner scanner = new Scanner(paths);
            List<String> fileNames = new ArrayList<String>();

            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("CREATE TABLE")) {
                    line = line.replace("CREATE TABLE", "");
                    line = line.replace("(", "");
                    line = line.replace(" ", "");
                    fileNames.add(line);

                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        line = line.replace(",", "");
                        if (line.contains(";")) {
                            break;
                        }

                        StringTokenizer tokenizer = new StringTokenizer(line);
                        String columnName = tokenizer.nextToken();
                        String type = tokenizer.nextToken();
                        String comment = getComment(line);

                        System.out.printf("%s   %s   %s \n", columnName, type, comment);
                    }

                    System.out.println();
                }
            }

            FileUtils.createFiles(fileNames);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getComment(String line) {
        Pattern pattern = Pattern.compile("--");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return "jest comment";
        }

        return "";
    }

}
