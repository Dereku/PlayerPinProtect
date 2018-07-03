package club.without.dereku.playerpinprotect;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public final class PlayerPinProtect extends JavaPlugin {

    private final static Gson GSON = new Gson();
    private Configuration configuration;

    @Override
    public void onEnable() {
        this.configuration = this.reloadConfiguration();
        //Configuration test
        this.getLogger().info(this.getConfiguration().toString());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public FileConfiguration getConfig() {
        throw new UnsupportedOperationException("Use PlayerPinProtect#getConfiguration()");
    }

    private Configuration reloadConfiguration() {
        final File file = new File(this.getDataFolder(), "config.json");
        Configuration result;
        //Load configuration
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
                result = PlayerPinProtect.GSON.fromJson(isr, Configuration.class);
            } catch (JsonSyntaxException | IOException e) {
                this.getLogger().log(Level.WARNING, "Failed to read " + file.getName(), e);
                result = new Configuration();
            }
        } else {
            result = new Configuration();
        }

        //Update configuration
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            final JsonWriter jsonWriter = PlayerPinProtect.GSON.newJsonWriter(osw);
            jsonWriter.setIndent("    "); // 4 spaces
            jsonWriter.setSerializeNulls(false);
            PlayerPinProtect.GSON.toJson(result, Configuration.class, jsonWriter);
        } catch (IOException e) {
            this.getLogger().log(Level.WARNING, "Failed to write " + file.getName(), e);
        }

        return result;
    }
}
