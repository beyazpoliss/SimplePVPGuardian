package tr.beyazpolis.net.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.SimplePvpGuardian;

public class BukkitEventListener implements Listener {

  private final SimplePvpGuardian plugin;

  public BukkitEventListener(final SimplePvpGuardian plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onJoin(@NotNull final PlayerJoinEvent event){
    Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
      plugin.getProfileManager().enter(event.getPlayer().getUniqueId());
      if (plugin.getProfileManager().of(event.getPlayer().getUniqueId()).getLeaveNumber() > plugin.getConfigManager().getMaxLiveNumber()){
        Bukkit.getScheduler().runTask(plugin, () -> event.getPlayer().setGameMode(GameMode.SPECTATOR));
      }
    });
  }

  @EventHandler
  public void onTeleport(@NotNull final PlayerTeleportEvent event){
    if (event.isCancelled()) return;
    if (!this.plugin.getCombatGuardian().hasWar(event.getPlayer().getUniqueId())) return;
    event.setCancelled(true);
  }

  @EventHandler
  public void onDamage(@NotNull final EntityDamageByEntityEvent event){
    if (event.isCancelled()) return;
    if (!(event.getDamager() instanceof Player)) return;
    if (!(event.getEntity() instanceof Player)) return;

    final Player damager = (Player) event.getDamager();
    final Player victim = (Player) event.getEntity();

    this.plugin.getProfileManager()
      .of(damager.getUniqueId())
      .getTimer()
      .combat(plugin.getCombatGuardian());
    this.plugin.getProfileManager()
      .of(victim.getUniqueId())
      .getTimer()
      .combat(plugin.getCombatGuardian());
  }

  @EventHandler
  public void onQuit(@NotNull final PlayerQuitEvent event){
    if (plugin.getCombatGuardian().hasWar(event.getPlayer().getUniqueId())){
     event.getPlayer().damage(500);
     plugin.getProfileManager().of(event.getPlayer().getUniqueId()).addLeaveNumber();
     plugin.getProfileManager().of(event.getPlayer().getUniqueId()).getTimer().setTime(-1);
    }
    Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> plugin.getProfileManager().disconnect(event.getPlayer().getUniqueId()));
  }

  public SimplePvpGuardian getPlugin() {
    return plugin;
  }
}
