package org.caffeine.newPlayerBook.items;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.caffeine.newPlayerBook.config.BookConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咖绯ん_caffeine
 * &#064;description  书本构建器
 */
public class BookBuilder {

    /**
     * 构建书本物品
     *
     * @param config 书本配置
     * @return 构建完成的 ItemStack
     */
    public static ItemStack buildBook(BookConfig config) {
        // 创建书本物品栈
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        // 设置书名，支持 MiniMessage
        if (config.getBookName() != null && !config.getBookName().isEmpty()) {
            meta.customName(parseText(config.getBookName()));
        }

        // 设置作者和标题
        meta.author(config.getAuthor() != null ? Component.text(config.getAuthor()) : null);
        meta.title(config.getTitle() != null ? Component.text(config.getTitle()) : null);

        // 设置书页，支持 MiniMessage 和 § 颜色码
        List<Component> pages = processBookPages(config.getPages());
        for (Component page : pages) {
            meta.addPages(page);
        }

        // 应用附魔
        applyEnchants(book, config.getEnchants());

        // 应用元数据
        book.setItemMeta(meta);
        return book;
    }

    /**
     * 处理书本页面内容，支持 MiniMessage 和 § 颜色代码
     *
     * @param pages 原始页面内容
     * @return 解析后的 Component 页面列表
     */
    private static List<Component> processBookPages(List<String> pages) {
        List<Component> processedPages = new ArrayList<>();
        if (pages == null) {
            return processedPages;
        }

        for (String page : pages) {
            processedPages.add(parseText(page));
        }
        return processedPages;
    }

    /**
     * 解析字符串为 Component，优先尝试 MiniMessage 格式，失败则解析 § 颜色码
     *
     * @param text 要解析的文本
     * @return 解析后的 Component
     */
    private static Component parseText(String text) {
        try {
            return MiniMessage.miniMessage().deserialize(text);
        } catch (Exception e) {
            return LegacyComponentSerializer.legacySection().deserialize(text);
        }
    }

    /**
     * 使用 RegistryAccess 获取 Enchantment 并添加到物品
     *
     * @param item     目标物品
     * @param enchants 附魔列表（格式如 "sharpness:5"）
     */
    private static void applyEnchants(ItemStack item, List<String> enchants) {
        if (enchants == null || enchants.isEmpty()) {
            return;
        }

        item.editMeta((ItemMeta meta) -> {
            for (String enchantStr : enchants) {
                String[] parts = enchantStr.split(":");
                if (parts.length != 2) {
                    continue;
                }

                // 获取 enchantment key 和等级
                NamespacedKey key = NamespacedKey.minecraft(parts[0].toLowerCase());
                int level;
                try {
                    level = Integer.parseInt(parts[1]);
                } catch (NumberFormatException ignored) {
                    continue;
                }

                // 从注册表中查找 Enchantment（安全 null 判断）
                Enchantment enchantment = RegistryAccess.registryAccess()
                        .getRegistry(RegistryKey.ENCHANTMENT)
                        .get(key);

                if (enchantment == null) {
                    continue;
                }

                // 添加附魔（忽略等级上限）
                meta.addEnchant(enchantment, level, true);
            }
        });
    }
}
