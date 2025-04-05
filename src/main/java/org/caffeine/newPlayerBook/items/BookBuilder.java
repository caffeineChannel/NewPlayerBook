package org.caffeine.newPlayerBook.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.caffeine.newPlayerBook.config.BookConfig;

import java.util.List;
/**
 * @author 咖绯ん_caffeine
 * &#064;description  书本构建器
 */
public class BookBuilder {
    public static ItemStack buildBook(BookConfig config) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        // 设置书本名称
        if (config.getBookName() != null && !config.getBookName().isEmpty()) {
            meta.setDisplayName(config.getBookName());
        }

        // 设置书本属性
        meta.setTitle(config.getTitle());
        meta.setAuthor(config.getAuthor());
        meta.setPages(config.getPages());

        // 应用附魔
        applyEnchants(book, config.getEnchants());

        book.setItemMeta(meta);
        return book;
    }

    private static void applyEnchants(ItemStack item, List<String> enchants) {
        if (enchants == null || enchants.isEmpty()) {
            return;
        }

        for (String enchantStr : enchants) {
            String[] parts = enchantStr.split(":");
            if (parts.length == 2) {
                try {
                    Enchantment enchant = Enchantment.getByName(parts[0].toUpperCase());
                    int level = Integer.parseInt(parts[1]);
                    if (enchant != null) {
                        item.addUnsafeEnchantment(enchant, level);
                    }
                } catch (NumberFormatException e) {
                    // 忽略无效的附魔等级
                }
            }
        }
    }
}