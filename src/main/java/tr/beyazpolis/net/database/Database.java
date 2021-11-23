package tr.beyazpolis.net.database;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.net.profile.FighterProfile;

public interface Database {

  @Nullable
  FighterProfile load(@NotNull final UUID uuid);

  @Nullable
  FighterProfile save(@NotNull final FighterProfile fighterProfile);

  void enable();

  void disable();

}
