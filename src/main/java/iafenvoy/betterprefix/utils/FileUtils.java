package iafenvoy.betterprefix.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

public class FileUtils {
  public static String readFile(String path) throws IOException {
    InputStream inputStream = Files.newInputStream(new File(path).toPath());
    StringBuilder stringBuilder = new StringBuilder();
    int i;
    while ((i = inputStream.read()) != -1)
      stringBuilder.append((char) i);
    inputStream.close();
    return stringBuilder.toString();
  }

  public static void saveFile(String path, String content) throws IOException {
    OutputStream outputStream = Files.newOutputStream(new File(path).toPath());
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    bufferedWriter.write(content);
    bufferedWriter.close();
  }
}
