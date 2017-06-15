package njupt;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by AoEiuV020 on 2017.06.15-14:12:55.
 */
public class WriterApp {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        File file = new File(System.getProperty("java.io.tmpdir"), "njupt.txt");
        Files.write(Paths.get(file.getAbsolutePath()), str.getBytes());
    }
}
