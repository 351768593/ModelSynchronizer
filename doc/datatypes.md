# 关于数据类型

下面列出默认检查器使用的 `@FieldDetail` 使用细则.

> **示例** 内省略 `@FieldDetail`, 仅保留括号; 且省略 `JDBCType` 静态引用  
> 如 `(type = BIT, length = 64)` 表示 `@FieldDetail(type = JDBCType.BIT, length = 64)`

MySQL 数据类型 | length 范围 | scale 范围 | 示例
---|---|---|---
bit | 1~64 | (无效) | `(type = BIT, length = 16)`
tinyint | (无效) | (无效) | `(type = TINYINT)`
smallint | (无效) | (无效) | `(type = SMALLINT)`
integer | (无效) | (无效) | `(type = INTEGER)`
bigint | (无效) | (无效) | `(type = BIGINT)`
boolean | (无效) | (无效) | `(type = BOOLEAN)`
decimal | 1~65 | 1~65 | `(type = DECIMAL, length = 12, scale = 2)`
numeric | 1~65 | 1~65 | `(type = NUMERIC, length = 12, scale = 2)`
char | 0~255 | (无效)
varchar | 0~65535 | (无效)
longvarchar |
nchar |
nvarchar |
longnvarchar |


## 不支持的数据类型

默认实现未对 `JDBCType` 中出现的所有数据类型提供支持. 下面是未作支持的数据类型列表和相应原因:

* `real`, `float`, `double`: 根据 [MySQL8 文档](https://dev.mysql.com/doc/refman/8.0/en/floating-point-types.html), 这几个数据类型已被标记为过时且将在未来更新中移除.  
  > As of MySQL 8.0.17, the nonstandard FLOAT(M,D) and DOUBLE(M,D) syntax is deprecated and you should expect support for it to be removed in a future version of MySQL.
* `null`: 发生什么事了

