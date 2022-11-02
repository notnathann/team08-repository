package group08.boeing.util;

import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static String readFile(String filePath) throws IOException {
        File indexFile = new File(filePath);
        Path indexPath = indexFile.toPath();

        System.out.println("Reading: " + indexFile.getAbsolutePath());

        return Files.readString(indexPath, StandardCharsets.US_ASCII);
    }
}
