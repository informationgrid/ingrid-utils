package de.ingrid.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestUtils {

    public static void writeFile(File file, List lines) throws IOException {
        file.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < lines.size(); i++) {
            writer.write((String) lines.get(i));
            writer.newLine();
        }
        writer.close();
    }
}
