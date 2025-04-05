package org.caffeine.newPlayerBook.i18n;

import org.bukkit.configuration.file.YamlConfiguration;
import org.caffeine.newPlayerBook.NewPlayerBook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author 咖绯ん_caffeine
 * &#064;description: 消息管理器
 */
public class MessageManager {
    private final NewPlayerBook plugin;
    private YamlConfiguration messages;

    public MessageManager(NewPlayerBook plugin) {
        this.plugin = plugin;
    }

    public void loadMessages(String language) {
        // 定义语言文件路径（兼容resources/messages结构）
        String resourcePath = "messages/" + language + ".yml";
        File langFile = new File(plugin.getDataFolder(), resourcePath);

        // 确保父目录存在
        langFile.getParentFile().mkdirs();

        // 如果文件不存在，从JAR释放（不覆盖现有文件）
        if (!langFile.exists()) {
            plugin.saveResource(resourcePath, false);
            // 注：saveResource()的路径不能以/开头
        }

        // 加载物理文件
        messages = YamlConfiguration.loadConfiguration(langFile);

        // 加载JAR内默认配置作为保底
        try (InputStream defaultStream = plugin.getResource(resourcePath)) {
            if (defaultStream != null) {
                messages.setDefaults(YamlConfiguration.loadConfiguration(
                        new InputStreamReader(defaultStream, StandardCharsets.UTF_8)));
            }
        } catch (IOException e) {
            plugin.getLogger().warning("[NewPlayerBook]load language error/加载翻译文件出错:" + language);
        }
    }

    public String getMessage(String key, Object... args) {
        String message = messages.getString(key);
        if (message == null) {
            return "Missing message: " + key;
        }
        return String.format(message, args);
    }
}