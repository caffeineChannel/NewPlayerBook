package org.caffeine.newPlayerBook;

import org.bukkit.plugin.java.JavaPlugin;
import org.caffeine.newPlayerBook.commands.BookTabCompleter;
import org.caffeine.newPlayerBook.commands.CommandManager;
import org.caffeine.newPlayerBook.config.ConfigManager;
import org.caffeine.newPlayerBook.i18n.MessageManager;
import org.caffeine.newPlayerBook.listeners.PlayerJoinListener;

import java.util.Objects;

/**
 * @author 咖绯ん_caffeine
 * &#064;description 插件主类
 */
public final class NewPlayerBook extends JavaPlugin {
    private ConfigManager configManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        // 优先加载基础配置
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // 初始化消息管理器（依赖configManager的语言设置）
        messageManager = new MessageManager(this);
        messageManager.loadMessages(configManager.getLanguage());

        // 初始化命令和事件（传递messageManager）
        CommandManager commandManager = new CommandManager(this);
        Objects.requireNonNull(getCommand("npbook")).setExecutor(commandManager);
        // 注册命令执行器和 Tab 补全
        Objects.requireNonNull(getCommand("npbook")).setTabCompleter(new BookTabCompleter());

        getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(this), this);

        // 使用国际化日志
        printSplashArt();
        logLocalized("console.enable");

    }

    @Override
    public void onDisable() {
        logLocalized("console.disable");
    }

    // 国际化日志方法
    private void logLocalized(String key) {
        getLogger().info(messageManager.getMessage(key));
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }


    private void printSplashArt() {
        String color = "\u001B[32m";
        String reset = "\u001B[0m";

        String[] splashLines = new String[]{
                color + " _   _ ____   ____              _    " + reset,
                color + "| \\ | |  _ \\ |  _ )  ___   ___ | | __" + reset,
                color + "|  \\| | |/ / |  _ \\ / _ \\ / _ \\| |/ /" + reset,
                color + "| |\\  | |    | |_) | (_) | (_) |   < " + reset,
                color + "|_| \\_|_|    |____/ \\___/ \\___/|_|\\_\\" + reset,
                color + "v" + getDescription().getVersion() + " | Running on " + getServer().getName() + reset,
                color + "By: " + getDescription().getAuthors().getFirst() + reset,
        };

        // 使用统一的 INFO 前缀打印
        for (String line : splashLines) {
            getLogger().info(line);
        }
    }
}