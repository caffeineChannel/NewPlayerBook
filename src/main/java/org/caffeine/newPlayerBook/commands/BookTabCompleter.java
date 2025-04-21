package org.caffeine.newPlayerBook.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 咖绯ん_caffeine
 * &#064;description 指令补全器
 */
public class BookTabCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command cmd,
            @NotNull String label,
            @NotNull String @NotNull [] args) {

        List<String> completions = new ArrayList<>();

        // 只处理 /npbook 指令
        if (!"npbook".equalsIgnoreCase(cmd.getName())) {
            return completions;
        }

        // 第一级参数补全：/npbook <subcommand>
        if (args.length == 1) {
            if (sender.hasPermission("npbook.give")) {
                completions.add("givebook");
            }
            if (sender.hasPermission("npbook.reload")) {
                completions.add("reload");
            }
            return filterCompletions(args[0], completions);
        }

        // 第二级参数补全：/npbook givebook [player]
        if (args.length == 2 && "givebook".equalsIgnoreCase(args[0])) {
            // 返回所有在线玩家名（自动过滤输入）
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .collect(Collectors.toList());
        }

        return completions;
    }

    // 过滤匹配输入的部分
    private List<String> filterCompletions(String input, List<String> completions) {
        return completions.stream()
                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
                .collect(Collectors.toList());
    }
}