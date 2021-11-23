package tr.beyazpolis.net.manager;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.SimplePvpGuardian;
import tr.beyazpolis.net.guardian.CombatGuardian;

public class SchedulerManager {

  private final SimplePvpGuardian plugin;
  private final ProfileManager profileManager;

  public SchedulerManager(final SimplePvpGuardian plugin,
                          final ProfileManager profileManager) {
    this.plugin = plugin;
    this.profileManager = profileManager;
  }

  public void run(@NotNull final CombatGuardian combatGuardian){
    Bukkit.getScheduler().runTaskTimer(plugin, () -> {
      if (profileManager.getPROFILES().isEmpty()) return;
      profileManager.getPROFILES().forEach((uuid, fighterProfile) -> {
        fighterProfile.getTimer().run(combatGuardian);
      });
    },20,20);
  }
}
