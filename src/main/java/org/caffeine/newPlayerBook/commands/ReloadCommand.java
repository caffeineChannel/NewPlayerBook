package org.caffeine.newPlayerBook.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.caffeine.newPlayerBook.NewPlayerBook;
import org.jetbrains.annotations.NotNull;

/**
 * @author 咖绯ん_caffeine
 * &#064;description  重载命令
 */
public class ReloadCommand implements CommandExecutor {
    private final NewPlayerBook plugin;

    public ReloadCommand(NewPlayerBook plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission("npbook.reload")) {
            sender.sendMessage(plugin.getMessageManager()
                    .getMessage("no-permission"));
            return true;
        }

        try {
            plugin.reloadConfig();
            plugin.getConfigManager().loadConfig();
        } catch (Exception e) {
            sender.sendMessage(plugin.getMessageManager()
                    .getMessage("feedback.reload-failed")+ ":" + e.getMessage());
        }
        sender.sendMessage(plugin.getMessageManager()
                .getMessage("feedback.reload-success"));
        return true;
    }
}