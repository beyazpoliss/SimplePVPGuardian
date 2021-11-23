package tr.beyazpolis.net.manager;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import tr.beyazpolis.net.SimplePvpGuardian;
import tr.beyazpolis.net.database.Database;
import tr.beyazpolis.net.database.types.YamlDatabase;

public class DatabaseManager {

  private final SimplePvpGuardian plugin;

  private final ConfigManager configManager;
  private Database database;

  public DatabaseManager(@NotNull final SimplePvpGuardian instance,
                         final ConfigManager configManager) {
    this.plugin = instance;
    this.configManager = configManager;
  }

  public void enable(){
    final boolean yaml = configManager.getSettingsConfig().getConfiguration().getBoolean("databaseTypes.local.yaml.on");
    final boolean mongo = configManager.getSettingsConfig().getConfiguration().getBoolean("databaseTypes.MongoDB.on");
    if (yaml){
      this.database = new YamlDatabase(plugin);
    } else if (mongo){

    } else {
      this.database = new YamlDatabase(plugin);
    }
    database.enable();
  }

  public void disable(){
    database.disable();
  }

  public ConfigManager getConfigManager() {
    return configManager;
  }

  public Database getDatabase() {
    return database;
  }

  public SimplePvpGuardian getPlugin() {
    return plugin;
  }
}
