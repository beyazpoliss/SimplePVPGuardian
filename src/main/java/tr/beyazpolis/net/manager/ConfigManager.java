package tr.beyazpolis.net.manager;

import tr.beyazpolis.net.SimplePvpGuardian;
import tr.beyazpolis.net.config.SettingsConfig;

public class ConfigManager {

  private final SimplePvpGuardian plugin;
  private final SettingsConfig settingsConfig;

  public ConfigManager(final SimplePvpGuardian plugin) {
    this.plugin = plugin;
    this.settingsConfig = new SettingsConfig(plugin);
  }

  public void start(){
    settingsConfig.loadYML();
    settingsConfig.setIfNotExists("timer.counterTime",15);
    settingsConfig.setIfNotExists("timer.maxLeaveNumber",5);
    settingsConfig.setIfNotExists("databaseTypes.local.yaml.on",true);
    settingsConfig.setIfNotExists("databaseTypes.MongoDB.on",false);
    settingsConfig.setIfNotExists("databaseTypes.MongoDB.passwordLink","");
    settingsConfig.saveYML();
  }

  public void reload(){
    settingsConfig.saveYML();
    settingsConfig.loadYML();
  }

  public void save(){
    settingsConfig.saveYML();
  }

  public SettingsConfig getSettingsConfig() {
    return settingsConfig;
  }

  public int getWarTime(){
    return settingsConfig.getConfiguration().getInt("timer.counterTime",15);
  }

  public int getMaxLiveNumber(){
    return settingsConfig.getConfiguration().getInt("timer.maxLeaveNumber",5);
  }


  public SimplePvpGuardian getPlugin() {
    return plugin;
  }
}
