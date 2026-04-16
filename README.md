# GankApp

一个基于旧版 `gank.io` 开放接口实现的 Android 客户端示例项目，使用 Java、MVP、Volley 和 Material Design 风格构建，适合作为早期 Android 内容类应用的学习样例。

## 项目概览

这个项目围绕 `gank.io` 的历史内容接口组织内容展示，首页通过 `ViewPager + Fragment` 切分为 3 个主要模块：

- `干货`：拉取历史文章内容列表，点击后进入 `WebView` 详情页阅读。
- `福利`：以瀑布流图片墙形式展示福利图片，点击后进入大图页。
- `移动端`：进一步拆分为 `Android` 和 `iOS` 两个子分类列表。

除此之外，首页还带有一个左侧抽屉和头像区域，支持从相册选择图片作为头像展示。

## 功能特性

- 基于 `MVP` 的页面分层，`Fragment / Presenter / Model` 结构清晰。
- 使用 `Volley` 封装网络请求，并结合 `Gson` 进行响应解析。
- 内置图片加载与二级缓存实现：内存缓存 + 磁盘缓存。
- `福利` 页面使用 `RecyclerView + StaggeredGridLayoutManager` 展示图片瀑布流。
- `干货` 页面支持下拉刷新，内容页通过 `WebView` 呈现 HTML 正文。
- `移动端` 页面按 `Android / iOS` 分类展示内容列表。

## 技术栈

- Java
- Android Support Library `24.0.0`
- RecyclerView
- Volley
- Gson `2.2.1`
- JUnit 4
- Mockito
- Gradle `2.10`
- Android Gradle Plugin `2.1.0`

## 运行环境

从仓库配置来看，这个项目是一个典型的老版本 Android 工程：

- `compileSdkVersion 24`
- `targetSdkVersion 24`
- `minSdkVersion 15`
- Gradle Wrapper：`2.10`
- Android Gradle Plugin：`2.1.0`

建议使用接近项目年代的环境打开，例如：

- Android Studio 2.x 系列
- JDK 8
- 已安装 Android SDK 24 / Build Tools 24.0.0

## 快速开始

1. 克隆仓库。
2. 使用 Android Studio 打开项目根目录。
3. 确认本机安装了 Android SDK 24 和对应 Build Tools。
4. 使用 JDK 8 作为 Gradle/JVM。
5. 同步 Gradle 并运行 `app` 模块。

如果你想在命令行尝试构建：

```bash
./gradlew assembleDebug
```

## 接口说明

项目中的接口地址定义在 [ProConstant.java](app/src/main/java/com/himan/himanpro/core/ProConstant.java)：

- 历史内容：`http://gank.io/api/history/content/{count}/{page}`
- 随机分类数据：`http://gank.io/api/random/data/{sort}/{count}`

当前代码直接依赖这些历史 HTTP 接口。如果接口返回格式变更、地址失效，或者服务不可访问，应用的数据加载会受到影响，需要自行替换为可用接口。

## 项目结构

```text
GankApp/
├── app/
│   ├── src/main/java/com/himan/himanpro/
│   │   ├── activity/      # 页面 Activity
│   │   ├── adapter/       # ListView / RecyclerView 适配器
│   │   ├── core/          # BaseActivity、BaseFragment、全局常量
│   │   ├── domain/        # 接口返回实体
│   │   ├── fragment/      # 干货 / 福利 / 移动端页面
│   │   ├── mvp/           # MVP 分层实现
│   │   ├── net/           # Volley 请求、缓存、响应封装
│   │   ├── utils/         # 工具类
│   │   └── view/          # 自定义 View
│   └── src/main/res/      # 布局、样式、图片资源
├── gradle/
├── build.gradle
└── README.md
```

## 关键页面

- [AppStartActivity.java](app/src/main/java/com/himan/himanpro/activity/AppStartActivity.java)：应用首页，负责 `ViewPager`、底部切换和抽屉布局。
- [GanHuoFragment.java](app/src/main/java/com/himan/himanpro/fragment/GanHuoFragment.java)：干货列表与下拉刷新。
- [FuLiFragment.java](app/src/main/java/com/himan/himanpro/fragment/FuLiFragment.java)：福利图片流。
- [YiDongFragment.java](app/src/main/java/com/himan/himanpro/fragment/YiDongFragment.java)：移动端分类切换容器。
- [HttpLoader.java](app/src/main/java/com/himan/himanpro/net/HttpLoader.java)：网络层入口。
- [LoadData.java](app/src/main/java/com/himan/himanpro/mvp/model/LoadData.java)：数据获取与回调分发。

## 已知限制

- 仓库依赖的构建链较老，直接在现代 Java 环境下无法构建。
- 在当前机器上执行 `./gradlew test` 时，实际报错为：Gradle `2.10` 无法识别 `Java 21.0.9`。
- 网络接口使用 `http://` 历史地址，不适合直接作为现代线上项目模板。
- 仓库当前测试覆盖较少，现有单元测试基本是占位状态。

## 适合用来做什么

- 学习早期 Android Java 项目的页面组织方式。
- 参考传统 MVP + Volley 的分层写法。
- 作为旧项目升级改造的练手样本。
- 对照现代 Android 项目，逐步迁移到 AndroidX、Retrofit、ViewBinding、Kotlin 等新方案。

## License

仓库中未看到明确的开源许可证文件；如果你准备公开分发或二次使用，建议先补充 `LICENSE`。
