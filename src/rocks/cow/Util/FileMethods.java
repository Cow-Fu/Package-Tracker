package rocks.cow.Util;

import java.io.*;

public class FileMethods {
    private File file;

    public FileMethods(String file) {
        this(new File(file));
    }

    public FileMethods(File file) {
        this.file = file;
    }

    public String readFullFile() {
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


    public void write(String text) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(this.file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
