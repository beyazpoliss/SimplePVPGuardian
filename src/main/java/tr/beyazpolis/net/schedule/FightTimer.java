package tr.beyazpolis.net.schedule;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.guardian.CombatGuardian;

public class FightTimer implements CombatTimer {

  private int time;
  private final UUID uuid;
  private final BossBar bossBar;
  private double b = 1.0;

  public FightTimer(@NotNull final UUID uuid){
    this.uuid = uuid;
    time = -1;
    this.bossBar = Bukkit.createBossBar("" + time, BarColor.YELLOW, BarStyle.SOLID, BarFlag.DARKEN_SKY);
  }

  @Override
  public int getTime() {
    return time;
  }

  @Override
  public void run(final @NotNull CombatGuardian combatGuardian) {
    if (time == -1){
      if (combatGuardian.hasWar(uuid)){
        combatGuardian.removePlayer(uuid);
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null){
          bossBar.removePlayer(player);
        }
        b = 1.0;
      }
      return;
    } else {
      double number = combatGuardian.getWarTime() + 0.0;
      if (time <= 0){
        bossBar.setProgress(0);
      } else if (time == combatGuardian.getWarTime()){
        bossBar.setProgress(1);
      } else {
        double a = 1.0 / number;
        b -= a;
        bossBar.setProgress(b);
      }
      bossBar.setTitle(time + "");
      final Player player = Bukkit.getPlayer(uuid);
      if (player != null){
        bossBar.addPlayer(player);
      }
    }
    time--;
  }

  @Override
  public void combat(final @NotNull CombatGuardian combatGuardian) {
    time = combatGuardian.getWarTime();
    combatGuardian.addPlayerWar(uuid);
    b = 1.0;
  }

  @Override
  public void setTime(final int time) {
    this.time = time;
  }

  @Override
  public UUID getUUID() {
    return uuid;
  }

  public static void main(String[] args){
    double b = 1;
    double number = 15 + 0.0;
    double a = 1.0 / number;
    b -= a;
    System.out.println(b);
    b -=a;
    System.out.println(b);

  }
}
