package org.caffeine.newPlayerBook.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.caffeine.newPlayerBook.NewPlayerBook;
import org.jetbrains.annotations.NotNull;

/**
 * @author 咖绯ん_caffeine
 * &#064;description  命令管理器
 */
public class CommandManager implements CommandExecutor {
    private final NewPlayerBook plugin;
    private final GiveBookCommand giveBookCommand;
    private final ReloadCommand reloadCommand;

    public CommandManager(NewPlayerBook plugin) {
        this.plugin = plugin;
        this.giveBookCommand = new GiveBookCommand(plugin);
        this.reloadCommand = new ReloadCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("npbook.give")) {
                sender.sendMessage(plugin.getMessageManager()
                        .getMessage("no-permission"));
                return true;
            }
            // 发送国际化命令帮助
            sender.sendMessage(plugin.getMessageManager().getMessage("commands.help.header"));
            sender.sendMessage(plugin.getMessageManager().getMessage("commands.help.givebook"));
            sender.sendMessage(plugin.getMessageManager().getMessage("commands.help.reload"));
            return true;
        }

        return switch (args[0].toLowerCase()) {
            case "givebook" -> giveBookCommand.onCommand(sender, cmd, label, args);
            case "reload" -> reloadCommand.onCommand(sender, cmd, label, args);
            default -> {
                // 发送未知命令提示
                sender.sendMessage(plugin.getMessageManager().getMessage("commands.unknown"));
                yield true;
            }
        };
    }
}