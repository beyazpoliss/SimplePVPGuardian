package tr.beyazpolis.net.schedule;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.guardian.CombatGuardian;
import tr.beyazpolis.net.manager.ProfileManager;

public interface CombatTimer {

  UUID getUUID();

  int getTime();

  void run(final @NotNull CombatGuardian combatGuardian);

  void combat(final @NotNull CombatGuardian combatGuardian);

  void setTime(final int time);

}
