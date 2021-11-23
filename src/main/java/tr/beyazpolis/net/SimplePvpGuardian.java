package tr.beyazpolis.net;

import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tr.beyazpolis.net.guardian.CGuardian;
import tr.beyazpolis.net.guardian.CombatGuardian;
import tr.beyazpolis.net.listener.BukkitEventListener;
import tr.beyazpolis.net.manager.ConfigManager;
import tr.beyazpolis.net.manager.DatabaseManager;
import tr.beyazpolis.net.manager.ProfileManager;
import tr.beyazpolis.net.manager.SchedulerManager;

public class SimplePvpGuardian extends JavaPlugin {

  private static SimplePvpGuardian INSTANCE;
  private ConfigManager configManager;
  private DatabaseManager databaseManager;

  private ProfileManager profileManager;
  private CombatGuardian combatGuardian;
  private SchedulerManager schedulerManager;

  @Override
  public void onEnable() {
    this.configManager = new ConfigManager(this);
    this.databaseManager = new DatabaseManager(this,configManager);
    this.profileManager = new ProfileManager(databaseManager);

    CompletableFuture.runAsync(() -> {

      configManager.start();
      databaseManager.enable();

      this.combatGuardian = new CGuardian(this);
      this.schedulerManager = new SchedulerManager(this,profileManager);

      schedulerManager.run(combatGuardian);
    });

    Bukkit.getPluginManager().registerEvents(new BukkitEventListener(this),this);
    getCommand("setleavenumber").setExecutor(new GuardianCommand(profileManager));
  }

  @Override
  public void onDisable() {
    this.databaseManager.disable();
  }

  public ProfileManager getProfileManager() {
    return profileManager;
  }

  public ConfigManager getConfigManager() {
    return configManager;
  }

  public DatabaseManager getDatabaseManager() {
    return databaseManager;
  }

  public static SimplePvpGuardian getINSTANCE() {
    return INSTANCE;
  }

  public CombatGuardian getCombatGuardian() {
    return combatGuardian;
  }

}
