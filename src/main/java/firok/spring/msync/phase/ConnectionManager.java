package firok.spring.msync.phase;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 管理数据库连接, 用于在子过程间共享数据库连接实例
 * @implNote 子类需在构造方法内建立数据库连接. 如果
 */
public interface ConnectionManager extends Closeable
{
	/**
	 * @return 获取一个可用数据库连接
	 * @throws SQLException 没有建立数据库连接时调用此方法会抛出
	 */
	Connection getConnection() throws SQLException;

	/**
	 * @return 获取当前连接指向的库名
	 */
	String getScheme();

//	/**
//	 * @param sql SQL字符串
//	 * @return 获取一个可供执行的SQL语句实例
//	 * @throws SQLException 没有建立数据库连接时调用此方法会抛出
//	 */
//	PreparedStatement getStatement(String sql) throws SQLException;

	/**
	 * 关闭管理器并释放所有资源
	 */
	void close();
}
