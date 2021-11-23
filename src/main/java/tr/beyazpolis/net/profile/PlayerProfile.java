package tr.beyazpolis.net.profile;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.schedule.CombatTimer;
import tr.beyazpolis.net.schedule.FightTimer;

public class PlayerProfile implements FighterProfile {

  private final UUID uuid;

  private final CombatTimer combatTimer;
  private int leaveNumber;

  public PlayerProfile(final UUID uuid) {
    this.uuid = uuid;
    this.leaveNumber = 0;
    this.combatTimer = new FightTimer(uuid);
  }

  @Override
  public UUID getUUID() {
    return uuid;
  }

  @Override
  public CombatTimer getTimer() {
    return combatTimer;
  }

  @Override
  public int getLeaveNumber() {
    return leaveNumber;
  }

  @Override
  public void addLeaveNumber() {
    leaveNumber++;
  }

  public void setLeaveNumber(final int leaveNumber) {
    this.leaveNumber = leaveNumber;
  }
}
