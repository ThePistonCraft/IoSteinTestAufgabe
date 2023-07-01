package de.eincode.iostein.cases.command;

import de.eincode.iostein.cases.CaseSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoinCommand
        implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        final Player player = (Player) sender;

        if (!player.hasPermission("iostein.coin")) {
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Dazu hast du keine Rechte.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/coins <player> <set/add/remove> <amount>");
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Bitte benutze: §e/coins <player> <reset>");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Der Spieler §e" + args[0] + " §7wurde nicht gefunden.");
            return true;
        }

        if (args[1].equalsIgnoreCase("set")) {
            int amount = Integer.parseInt(args[2]);

            CaseSystem.getInstance().getCoinAPI().setPlayerCoins(target, amount);
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den Spieler §e" +
                    target.getName() + "§7, §e" + amount + " §7Coins gesetzt.");
        }
        if (args[1].equalsIgnoreCase("add")) {
            int amount = Integer.parseInt(args[2]);

            CaseSystem.getInstance().getCoinAPI().addPlayerCoins(target, amount);
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den Spieler §e" +
                    target.getName() + "§7, §e" + amount + " §7Coins hinzugefügt.");

        }
        if (args[1].equalsIgnoreCase("remove")) {
            int amount = Integer.parseInt(args[2]);

            CaseSystem.getInstance().getCoinAPI().removePlayerCoins(target, amount);
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den Spieler §e" +
                    target.getName() + "§7, §e" + amount + " §7Coins entfernt.");

        }
        if (args[1].equalsIgnoreCase("reset")) {
            CaseSystem.getInstance().getCoinAPI().setPlayerCoins(target, 0);
            player.sendMessage(CaseSystem.getInstance().getPrefix() + "Du hast den Spieler §e" +
                    target.getName() + " §7die Coins resettet.");

        }

        return false;
    }
}
