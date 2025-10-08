# DarvinPro AI 智能助手

一个基于 Spring Boot 3 和 Spring AI 开发的智能对话机器人，支持流式对话、对话历史管理、联网搜索等功能。

## 🌟 功能特性

- **智能对话**: 基于大语言模型的智能问答
- **流式响应**: 支持 SSE 流式输出，实时展示回答内容
- **对话管理**: 对话历史记录、重命名、删除等功能
- **联网搜索**: 集成 SearXNG 搜索引擎，支持联网获取最新信息
- **对话记忆**: 自动保存对话历史，提供上下文记忆能力
- **多模型支持**: 支持多种大语言模型切换
- **参数可调**: 支持温度值等参数调节

## 🏗️ 技术架构

- **后端框架**: Spring Boot 3.4.5
- **AI 框架**: Spring AI 1.0.0
- **数据库**: PostgreSQL
- **ORM 框架**: MyBatis-Plus 3.5.12
- **搜索引擎**: SearXNG
- **HTTP 客户端**: OkHttp 4.12.0
- **日志框架**: Log4j2
- **Java 版本**: Java 21

## 🚀 快速开始

### 环境要求

- Java 21+
- PostgreSQL 数据库
- SearXNG 搜索引擎实例
- 大语言模型 API Key (如阿里云百炼)

### 配置文件

1. 配置自己的.env 文件，并添加以下内容：

``` env
SPRING_DATASOURCE_USERNAME=your_database_username
SPRING_DATASOURCE_PASSWORD=your_database_password
SPRING_AI_OPENAI_API_KEY=your_api_key
```

### 数据库初始化

创建 PostgreSQL 数据库，并执行以下建表语句：

```sql
-- 聊天对话表
CREATE TABLE t_chat
(
    id BIGSERIAL PRIMARY KEY,                                          -- 主键
    uuid        VARCHAR(60) UNIQUE NOT NULL,                           -- 对话 UUID
    summary     VARCHAR(60),                                           -- 对话摘要
    create_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    update_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

-- 为表添加注释
COMMENT ON TABLE t_chat IS '聊天对话表';

-- 为字段添加注释
COMMENT ON COLUMN t_chat.id IS '主键ID，自增唯一标识';
COMMENT ON COLUMN t_chat.uuid IS '对话UUID，全局唯一标识';
COMMENT ON COLUMN t_chat.summary IS '对话摘要（最大长度60字符）';
COMMENT ON COLUMN t_chat.create_time IS '记录创建时间（默认当前时间）';
COMMENT ON COLUMN t_chat.update_time IS '记录最后更新时间（默认当前时间）';

-- 添加索引
CREATE INDEX idx_chat_update_time ON t_chat (update_time);


-- 聊天消息表
CREATE TABLE t_chat_message
(
    id BIGSERIAL PRIMARY KEY,                                  -- 主键ID，自增唯一标识
    chat_uuid   VARCHAR(60) NOT NULL,                          -- 关联的对话表的UUID
    content     TEXT        NOT NULL,                          -- 消息内容
    role        VARCHAR(12),                                   -- 消息角色（如：user/assistant）
    create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP -- 消息创建时间
);

-- 为表添加注释
COMMENT ON TABLE t_chat_message IS '聊天消息记录表';

-- 为字段添加注释
COMMENT ON COLUMN t_chat_message.id IS '主键ID，自增唯一标识';
COMMENT ON COLUMN t_chat_message.chat_uuid IS '关联的对话UUID，与t_chat表的uuid字段关联';
COMMENT ON COLUMN t_chat_message.content IS '消息内容';
COMMENT ON COLUMN t_chat_message.role IS '消息发送者角色（如：user-用户，assistant-助手）';
COMMENT ON COLUMN t_chat_message.create_time IS '消息创建时间（默认当前时间）';

-- 添加索引
create index idx_chat_message_create_time on t_chat_message (create_time);
create index idx_chat_message_uuid on t_chat_message (chat_uuid);

```

### 启动应用

```bash
# 克隆项目
git clone https://github.com/darven-cs/darvin-pro.git

git clone https://github.com/darven-cs/darvin-pro.git

# 进入项目目录
cd darvin-pro

# 编译打包
mvn clean package

# 运行应用
java -jar target/darvin-pro.jar
```

应用默认启动在 `http://localhost:8080`

## 📚 API 接口

### 对话相关

| 接口   | 方法   | 路径                     | 描述       |
|------|------|------------------------|----------|
| 新建对话 | POST | `/chat/new`            | 创建新的对话   |
| 流式对话 | POST | `/chat/completion`     | 进行流式对话   |
| 对话列表 | POST | `/chat/list`           | 获取对话历史列表 |
| 消息列表 | POST | `/chat/message/list`   | 获取对话消息列表 |
| 重命名  | POST | `/chat/summary/rename` | 重命名对话摘要  |
| 删除对话 | POST | `/chat/delete`         | 删除指定对话   |

## 🛠️ 核心组件

### Advisor 组件

- `CustomChatMemoryAdvisor`: 自定义对话记忆管理
- `CustomStreamLoggerAndMessage2DBAdvisor`: 流式日志记录和消息持久化
- `NetworkSearchAdvisor`: 联网搜索增强

### 服务组件

- `ChatService`: 对话核心业务逻辑
- `SearXNGService`: 搜索引擎集成服务
- `SearchResultContentFetcherService`: 搜索结果内容提取

## 📦 依赖管理

项目使用 Maven 进行依赖管理，主要依赖包括：

- Spring Boot Web Starter
- Spring AI OpenAI Starter
- MyBatis-Plus
- PostgreSQL Driver
- OkHttp
- Jsoup
- Hutool
- Guava

## 📝 开发指南

### 项目结构

```
src/main/java/com/darven/
├── advisor/          # AI Advisor 组件
├── aspect/           # AOP 切面
├── config/           # 配置类
├── constant/         # 常量定义
├── controller/       # 控制器
├── domain/           # 数据访问层
│   ├── dos/          # 数据对象
│   └── mapper/       # MyBatis Mapper
├── enums/            # 枚举类
├── exception/        # 异常处理
├── model/            # 数据模型
│   ├── common/       # 通用模型
│   ├── dto/          # 数据传输对象
│   └── vo/           # 视图对象
├── service/          # 业务服务
└── utils/            # 工具类
```

### 自定义配置

可通过修改 application.yml
激活不同环境配置：

```yaml
spring:
  profiles:
    active: dev  # 可选: dev, prod
```

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来帮助改进项目。

## 📄 许可证

本项目采用 MIT 许可证，详情请见 [LICENSE](LICENSE) 文件。

## 👨‍💻 作者

darven

---

> 🎉 喜欢这个项目？给它一个 Star 吧！⭐
