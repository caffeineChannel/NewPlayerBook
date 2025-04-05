package org.caffeine.newPlayerBook.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.caffeine.newPlayerBook.NewPlayerBook;

/**
 * @author 咖绯ん_caffeine
 * &#064;description 配置管理器
 */
public class ConfigManager {
    private final NewPlayerBook plugin;
    private BookConfig bookConfig;

    public ConfigManager(NewPlayerBook plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        bookConfig = new BookConfig(
                config.getString("give-mode", "new"),
                config.getString("book.name", null),
                config.getString("book.title", "欢迎指南"),
                config.getString("book.author", "服务器"),
                config.getStringList("book.pages"),
                config.getStringList("book.enchants")
        );

        if (bookConfig.getPages().isEmpty()) {
            bookConfig.getPages().add(plugin.getMessageManager().getMessage("book.default-welcome-page"));
        }
    }

    public BookConfig getBookConfig() {
        return bookConfig;
    }

    public String getLanguage() {
        return plugin.getConfig().getString("language", "zh_CN");
    }
}