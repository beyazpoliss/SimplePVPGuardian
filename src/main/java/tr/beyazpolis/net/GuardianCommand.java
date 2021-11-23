package tr.beyazpolis.net;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tr.beyazpolis.net.manager.ProfileManager;
import tr.beyazpolis.net.profile.FighterProfile;

public class GuardianCommand implements CommandExecutor {

  private final ProfileManager profileManager;

  public GuardianCommand(final ProfileManager profileManager) {
    this.profileManager = profileManager;
  }

  @Override
  public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, final @NotNull String[] args) {
    if (!sender.isOp()) return true;
    if (args.length == 1) {
      sender.sendMessage(ChatColor.GREEN + "/setLeaveNumber <player> <number>");
      return true;
    }
    if (args.length >= 1){
      final Player target = Bukkit.getPlayer(args[0]);
      if (target != null){
        if (target.isOnline()){
          final FighterProfile fighterProfile = profileManager.of(target.getUniqueId());
          if (fighterProfile != null){
            int a;
            try {
              a = Integer.parseInt(args[1]);
              profileManager.of(target.getUniqueId()).setLeaveNumber(a);
              target.setGameMode(GameMode.SURVIVAL);
            }catch (NumberFormatException e){
              sender.sendMessage(ChatColor.GREEN + "/setLeaveNumber <player> <number>");
            }
          }
        }
      }
    } else {
      sender.sendMessage(ChatColor.GREEN + "/setLeaveNumber <player> <number>");
    }
    //setLeaveNumber BeyazPolis 0
    return true;
  }
}
