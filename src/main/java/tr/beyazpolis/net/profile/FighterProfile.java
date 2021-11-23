package tr.beyazpolis.net.profile;

import java.util.UUID;
import tr.beyazpolis.net.schedule.CombatTimer;

public interface FighterProfile {

  UUID getUUID();

  CombatTimer getTimer();

  int getLeaveNumber();

  void addLeaveNumber();

  void setLeaveNumber(final int leaveNumber);

}
