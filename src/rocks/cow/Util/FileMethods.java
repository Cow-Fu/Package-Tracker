package rocks.cow.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileMethods {
    public static String readFullFile(File file) {
        byte[] data = null;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data == null) {
            return null;
        }

        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String readFullFile(String file) {
        return readFullFile(new File(file));
    }
}
