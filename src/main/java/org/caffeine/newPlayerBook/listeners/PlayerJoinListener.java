package org.caffeine.newPlayerBook.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.caffeine.newPlayerBook.NewPlayerBook;
import org.caffeine.newPlayerBook.config.BookConfig;
import org.caffeine.newPlayerBook.items.BookBuilder;

import java.util.Objects;

/**
 * @author 咖绯ん_caffeine
 * &#064;description 玩家加入事件监听器
 */
public class PlayerJoinListener implements Listener {
    private final NewPlayerBook plugin;

    public PlayerJoinListener(NewPlayerBook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String giveMode = plugin.getConfigManager().getBookConfig().getGiveMode();

        switch (giveMode) {
            case "all":
                giveBook(player);
                break;
            case "new":
                if (!player.hasPlayedBefore()) {
                    giveBook(player);
                }
                break;
            case "update":
                if (!hasCurrentBook(player)) {
                    giveBook(player);
                }
                break;
            default:
                // 默认按new模式处理
                if (!player.hasPlayedBefore()) {
                    giveBook(player);
                }
        }
    }

    private void giveBook(Player player) {
        ItemStack book = BookBuilder.buildBook(plugin.getConfigManager().getBookConfig());
        player.getInventory().addItem(book);
        player.sendMessage(plugin.getMessageManager()
                .getMessage("book.received"));
    }

    private boolean hasCurrentBook(Player player) {
        BookConfig config = plugin.getConfigManager().getBookConfig();

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.WRITTEN_BOOK) {
                BookMeta meta = (BookMeta) item.getItemMeta();
                if (meta != null && Objects.equals(meta.getTitle(), config.getTitle())) {
                    return true;
                }
            }
        }
        return false;
    }
}