package org.caffeine.newPlayerBook.config;

import java.util.List;
/**
 * @author 咖绯ん_caffeine
 * &#064;description 书籍配置类
 */
public class BookConfig {
    // 定义书籍的给予模式
    private final String giveMode;
    // 定义书籍的名称
    private final String bookName;
    // 定义书籍的标题
    private final String title;
    // 定义书籍的作者
    private final String author;
    // 定义书籍的页数
    private final List<String> pages;
    // 定义书籍的附魔
    private final List<String> enchants;

    // 构造函数，用于初始化书籍配置
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
    // 获取书籍的给予模式
    public String getGiveMode() { return giveMode; }
    // 获取书籍的名称
    public String getBookName() { return bookName; }
    // 获取书籍的标题
    public String getTitle() { return title; }
    // 获取书籍的作者
    public String getAuthor() { return author; }
    // 获取书籍的页数
    public List<String> getPages() { return pages; }
    // 获取书籍的附魔
    public List<String> getEnchants() { return enchants; }
}