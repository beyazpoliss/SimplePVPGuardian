package tr.beyazpolis.net.manager;

import java.util.HashMap;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.profile.FighterProfile;
import tr.beyazpolis.net.profile.PlayerProfile;

public class ProfileManager {

  private final DatabaseManager databaseManager;
  private final HashMap<UUID, FighterProfile> PROFILES;

  public ProfileManager(final DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
    this.PROFILES = new HashMap<>();
  }

  public void enter(@NotNull final UUID uuid){
    if (!PROFILES.containsKey(uuid)){
      final FighterProfile fighterProfile = databaseManager.getDatabase().load(uuid);
      if (fighterProfile == null){
        this.addProfile(uuid);
      } else {
        this.addProfile(uuid,fighterProfile);
      }
    }
  }

  public void disconnect(@NotNull final UUID uuid){
    if (PROFILES.get(uuid) != null){
      this.databaseManager.getDatabase().save(PROFILES.get(uuid));
    }
  }

  private void addProfile(@NotNull final UUID uuid){
    this.PROFILES.put(uuid, this.createDefaultProfile(uuid));
  }

  private void addProfile(@NotNull final UUID uuid,@NotNull final FighterProfile fighterProfile){
    this.PROFILES.put(uuid,fighterProfile);
  }

  public DatabaseManager getDatabaseManager() {
    return databaseManager;
  }

  public HashMap<UUID, FighterProfile> getPROFILES() {
    return PROFILES;
  }

  public FighterProfile of(@NotNull final UUID uuid){
    if (this.PROFILES.get(uuid) == null)
      return null;
    return this.PROFILES.get(uuid);
  }

  public FighterProfile createDefaultProfile(@NotNull final UUID uuid){
    return new PlayerProfile(uuid);
  }

}
