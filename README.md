#  test

    结构图：
    bootshop-5/
    ├── .gitattributes          # Git属性配置文件（控制文件换行符等）
    ├── README.md               # 项目说明文档（当前内容仅为"# test"）
    └── bootshop 5/             # 主项目目录（可能为项目子模块或版本目录）
    ├── .idea/              # IntelliJ IDEA项目配置目录
    │   ├── modules.xml     # 项目模块配置（指定模块文件路径）
    │   ├── misc.xml        # 杂项配置（如Maven项目路径、JDK版本等）
    │   ├── encodings.xml   # 编码配置（指定Java源码编码为UTF-8）
    │   ├── jarRepositories.xml  # Maven远程仓库配置（如中央仓库地址）
    │   └── compiler.xml    # 编译器配置（如注解处理器路径、编译参数）
    ├── __MACOSX/           # macOS系统生成的临时文件目录（通常可忽略）
    └── bootshop 2/         # 核心业务模块（Spring Boot应用主目录）
    ├── mvnw.cmd        # Maven包装器脚本（Windows下用于构建项目）
    ├── pom.xml         # Maven项目配置文件（依赖管理、插件配置等）
    ├── .gitignore      # Git忽略文件配置（如target目录、IDE配置文件等）
    ├── src/            # 源代码目录
    │   ├── main/       # 主程序代码
    │   │   ├── java/   # Java后端代码
    │   │   │   └── org/yiqixue/bootshop/  # 项目主包
    │   │   │       ├── BootshopApplication.java  # Spring Boot启动类（项目入口）
    │   │   │       ├── controller/  # 控制器层（处理HTTP请求）
    │   │   │       │   ├── BookController.java    # 基于Spring Data的书籍API控制器
    │   │   │       │   ├── BookJpaController.java  # 基于JPA的书籍API控制器
    │   │   │       │   ├── BookJdbcController.java # 基于JDBC的书籍API控制器
    │   │   │       │   └── WebController.java      # 页面跳转控制器（Thymeleaf模板）
    │   │   │       ├── service/  # 服务层（封装业务逻辑）
    │   │   │       │   ├── BookService.java       # 基于Spring Data的书籍服务
    │   │   │       │   └── BookJpaService.java    # 基于JPA的书籍服务
    │   │   │       └── entity/  # 实体类（映射数据库表）
    │   │   │           ├── Book.java       # 书籍实体（Spring Data映射）
    │   │   │           ├── User.java       # 用户实体（JPA映射）
    │   │   │           └── RealUser.java   # 扩展用户实体（含初始化逻辑）
    │   │   └── resources/  # 资源目录
    │   │       └── templates/  # 前端模板（Thymeleaf）
    │   │           ├── index.html          # 首页（欢迎页）
    │   │           ├── fragments.html      # 公共页面片段（页头、页脚、警告框）
    │   │           ├── user/list.html      # 用户列表页面
    │   │           └── mybatis-user/list.html  # MyBatis用户列表页面
    │   └── test/       # 测试代码目录
    │       └── java/org/yiqixue/bootshop/
    │           └── BootshopApplicationTests.java  # 应用启动测试类
