package njupt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by AoEiuV020 on 2017.06.15-14:12:55.
 */
public class WriterApp {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        File file = new File(System.getProperty("java.io.tmpdir"), "njupt.txt");
        OutputStream output = new FileOutputStream(file);
        output.write(str.getBytes());
        output.close();
    }
}
