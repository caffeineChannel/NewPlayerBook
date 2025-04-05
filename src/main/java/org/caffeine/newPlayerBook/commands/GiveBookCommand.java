package org.caffeine.newPlayerBook.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caffeine.newPlayerBook.NewPlayerBook;
import org.caffeine.newPlayerBook.items.BookBuilder;

/**
 * @author 咖绯ん_caffeine
 * &#064;description  发放新人书命令
 */
public class GiveBookCommand implements CommandExecutor {
    private final NewPlayerBook plugin;

    public GiveBookCommand(NewPlayerBook plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("npbook.give")) {
            sender.sendMessage(plugin.getMessageManager()
                    .getMessage("no-permission"));
            return true;
        }

        if (args.length > 1) {
            // 给指定玩家
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(plugin.getMessageManager()
                        .getMessage("feedback.player-not-found", args[1]));
                return true;
            }
            giveBook(target);
            sender.sendMessage(plugin.getMessageManager()
                    .getMessage("book.given-single", target.getName()));
        } else {
            // 给所有玩家
            int count = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                giveBook(player);
                count++;
            }
            sender.sendMessage(plugin.getMessageManager()
                    .getMessage("book.given-all", count));
        }
        return true;
    }

    private void giveBook(Player player) {
        player.getInventory().addItem(
                BookBuilder.buildBook(plugin.getConfigManager().getBookConfig())
        );
        player.sendMessage(plugin.getMessageManager()
                .getMessage("book.received"));
    }
}