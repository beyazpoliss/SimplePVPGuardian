package tr.beyazpolis.net.database.types;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.beyazpolis.net.SimplePvpGuardian;
import tr.beyazpolis.net.config.Config;
import tr.beyazpolis.net.database.Database;
import tr.beyazpolis.net.profile.FighterProfile;
import tr.beyazpolis.net.profile.PlayerProfile;

public class YamlDatabase implements Database {

  private final Config config;

  public YamlDatabase(@NotNull final SimplePvpGuardian pvpGuardian) {
    this.config = new Config(pvpGuardian,"data.yml");
  }

  @Override
  public @Nullable FighterProfile load(final @NotNull UUID uuid) {
    if (config.notSet(uuid.toString() + ".leaveNumber")){
      return null;
    }
    final FighterProfile fighterProfile = new PlayerProfile(uuid);
    fighterProfile.setLeaveNumber(config.getConfiguration().getInt(uuid.toString() + ".leaveNumber"));
    return fighterProfile;
  }

  @Override
  public @Nullable FighterProfile save(final @NotNull FighterProfile fighterProfile) {
    config.set(fighterProfile.getUUID().toString() + ".leaveNumber",fighterProfile.getLeaveNumber());
    config.saveYML();
    return fighterProfile;
  }

  @Override
  public void enable() {
    config.loadYML();
    config.saveYML();
  }

  @Override
  public void disable() {
    config.saveYML();
  }
}

