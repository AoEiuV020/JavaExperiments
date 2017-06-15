package njupt;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by AoEiuV020 on 2017.06.15-14:20:36.
 */
public class ReaderApp {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), "njupt.txt");
        String str = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        System.out.println(str);
    }
}
