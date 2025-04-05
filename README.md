<div align="center">

# NPBook(NewPlayerBook) - 新人书发放插件

![GitHub release](https://img.shields.io/github/v/release/caffeineChannel/NewPlayerBook?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-blue?style=flat-square)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21-green?style=flat-square)

一个非常简单的Paper(bukkit)端服务器插件(MC>=1.21)，用于向新加入服务器的玩家自动发放自定义书籍。

</div>

## 📦 安装
1. 下载 [最新版本](https://github.com/caffeineChannel/NewPlayerBook/releases/latest)
2. 放入MC服务端的 `plugins` 文件夹并重启服务端
3. 编辑 `plugins/NPBook/config.yml` 文件设置新人书内容及发放规则
4. 使用 `/npbook reload` 重载配置

## 🎯 功能
- 支持 Minecraft 1.21 Paper 端
- 自定义的书籍内容
- 支持 MC 颜色代码(§)
- 三种发放模式可选
- 热重载配置
- 批量发放功能

## 🔧 插件命令

| 命令 | 描述 | 权限 |
|------|------|------|
| `/npbook givebook [玩家名]` | 给指定玩家或所有在线玩家发放新人书 | `npbook.give` |
| `/npbook reload` | 重新加载配置文件 | `npbook.reload` |

> ⚠️ 如果省略玩家名，将发放给所有在线玩家。

---

## 📌 发放模式说明

- `new`：只给首次加入服务器的新玩家发书
- `all`：每次登录服务器都发书
- `update`：如果玩家没有当前版本的书（匹配标题）则发书

---

## ❤️ 开源许可

[MIT License](https://github.com/caffeineChannel/NewPlayerBook/blob/main/LICENSE) - 欢迎自由修改与使用，转载请注明[原作者](https://github.com/caffeineChannel)。

---

## 📬 联系与反馈

如果你在使用过程中发现 Bug 或有建议，欢迎通过 [Issue](https://github.com/caffeineChannel/NewPlayerBook/issues) 或 [Pull Request](https://github.com/caffeineChannel/NewPlayerBook/pulls) 反馈！当然反馈前可以先翻一翻，没准已经有人问过了 Ciallo～(∠・ω< )⌒☆
