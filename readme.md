# Model Synchronizer

> 项目仍在开发中, 尚未达到可用阶段

用于在项目启动时对比实体和相关数据库表结构并自动进行同步.

同步操作 **以实体结构为主**,  
会尝试将数据库结构调整至适应实体结构的状态.

> 能不能根据数据库结构自动调整实体结构?  
> 实体结构确定于 **编译期**,  
> 对比操作发生于 **运行期**;  
> 如果需要从数据库同步结构至类,  
> 留下的操作空间只剩在 **编译期** 使用 APT 直接操作类结构,  
> 或是在 **类加载阶段** 操作字节码修改类结构.  
> 这两种方法都会导致在代码里无法直接访问动态调整的结构.  
> ~~那是不是还有其它好办法呢? 我不知道, 我不管, 我懒得写~~
 
## 部分实现细节

同步过程抽象为以下几个子过程:

* 扫描实体信息
* 扫描数据库信息
* 对比实体和数据库信息, 获取结构差异
* 根据结构差异生成 SQL 代码
* 执行 SQL 代码

其中

* 各子过程抽象接口和默认实现都存于 `firok.spring.msync.phase` 包下
  * **默认的扫描实体信息实现基于 [MVCI 项目](https://github.com/351768593/MVCIntrospector) 的运行时实体类列表功能**
  * **默认的扫描数据库信息实现基于原生 MySQL 驱动和 MySQL 8 功能**
* 各过程间用到的中间数据类都存于 `firok.spring.msync.bean` 包下

> 由于子过程间均使用中间数据交付  
> 子步骤均可自由实现而互不影响

## 使用说明

### 使用默认实现

默认实现面向 MySQL8 数据库进行操作.  

* 在项目中引入 MySQL8 依赖并正确配置启用 MVCI
* 在需要同步的实体类上标注 `@TableDetail` 和 `@MVCIntrospective`
* 在需要同步的字段上标注 `@FieldDetail`

> MySQL8 驱动  
> [search on Maven](https://search.maven.org/artifact/mysql/mysql-connector-java)

#### 手动同步

调用如下代码即可:

```java
firok.spring.msync.phase.DefaultImplementations.sync_MVCI_MySQL(
        "db_url",
        "db_scheme",
        "db_username",
        "db_password"
);
```

### 使用自定义实现

TODO

## 可能出现的情况和相关可选操作

* ~~结构相同~~
  * ~~无需操作~~
* 结构仅于实体存在
  * 同步至数据库
  * 仅警告, 不操作
* 结构仅于数据库存在
  * 抛出异常
  * 仅警告, 不操作
* 结构于实体和数据库不对等
  * 同步至数据库
  * 仅警告, 不操作

