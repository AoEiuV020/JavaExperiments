package njupt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by AoEiuV020 on 2017.06.15-14:20:36.
 */
public class ReaderApp {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), "njupt.txt");
        InputStream input = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len;
        while ((len = input.read(buf)) > 0) {
            sb.append(new String(buf, 0, len));
        }
        System.out.println(sb.toString());
    }
}
