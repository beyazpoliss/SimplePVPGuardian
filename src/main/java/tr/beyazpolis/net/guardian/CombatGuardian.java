package tr.beyazpolis.net.guardian;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface CombatGuardian {

  boolean hasWar(@NotNull final UUID uuid);

  int getWarTime();

  void addPlayerWar(@NotNull final UUID uuid);

  void removePlayer(@NotNull final UUID uuid);
}
