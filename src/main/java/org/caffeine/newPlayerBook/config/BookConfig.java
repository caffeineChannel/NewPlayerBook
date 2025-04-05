package org.caffeine.newPlayerBook.config;

import java.util.List;
/**
 * @author 咖绯ん_caffeine
 * &#064;description 书籍配置类
 */
public class BookConfig {
    private final String giveMode;
    private final String bookName;
    private final String title;
    private final String author;
    private final List<String> pages;
    private final List<String> enchants;

    public BookConfig(String giveMode, String bookName,
                      String title, String author,
                      List<String> pages, List<String> enchants) {
        this.giveMode = giveMode;
        this.bookName = bookName;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.enchants = enchants;
    }

    // Getter 方法
    public String getGiveMode() { return giveMode; }
    public String getBookName() { return bookName; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public List<String> getPages() { return pages; }
    public List<String> getEnchants() { return enchants; }
}