package tr.beyazpolis.net.config;

import org.bukkit.plugin.Plugin;

public class SettingsConfig extends Config{

  public SettingsConfig(final Plugin plugin) {
    super(plugin, "settings.yml");
  }
}
