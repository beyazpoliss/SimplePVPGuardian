package tr.beyazpolis.net.guardian;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.SimplePvpGuardian;

public class CGuardian implements CombatGuardian {

  private final SimplePvpGuardian plugin;
  private final Set<UUID> WAR;

  private int warTime;

  public CGuardian(final SimplePvpGuardian plugin) {
    this.plugin = plugin;
    this.WAR = new HashSet<>();
    this.warTime = plugin.getConfigManager().getWarTime();
  }

  @Override
  public boolean hasWar(final @NotNull UUID uuid) {
    return WAR.contains(uuid);
  }

  @Override
  public int getWarTime() {
    return warTime;
  }

  @Override
  public void addPlayerWar(final @NotNull UUID uuid) {
    this.WAR.add(uuid);
  }

  @Override
  public void removePlayer(final @NotNull UUID uuid) {
    WAR.remove(uuid);
  }

  public SimplePvpGuardian getPlugin() {
    return plugin;
  }

  private Set<UUID> getWAR() {
    return WAR;
  }


}
