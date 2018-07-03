package club.without.dereku.playerpinprotect;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private final static Gson GSON = new Gson();

    public static Configuration loadConfiguration(File file, Logger logger) {
        if (!file.exists()) {
            return new Configuration();
        }

        Configuration configuration;
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            configuration = Utils.GSON.fromJson(isr, Configuration.class);
        } catch (JsonSyntaxException | IOException e) {
            if (logger != null) {
                logger.log(Level.WARNING, "Failed to read " + file.getName(), e);
            } else {
                e.printStackTrace();
            }
            return new Configuration();
        }

        return configuration;
    }

    public static void saveConfiguration(Configuration configuration, File file, Logger logger) {
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            final JsonWriter jsonWriter = Utils.GSON.newJsonWriter(osw);
            jsonWriter.setIndent("    "); // 4 spaces
            jsonWriter.setSerializeNulls(false);
            Utils.GSON.toJson(configuration, Configuration.class, jsonWriter);
        } catch (IOException e) {
            if (logger != null) {
                logger.log(Level.WARNING, "Failed to write " + file.getName(), e);
            } else {
                e.printStackTrace();
            }
        }
    }
}
