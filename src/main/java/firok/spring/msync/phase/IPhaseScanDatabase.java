package firok.spring.msync.phase;

import firok.spring.msync.bean.MetaScheme;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 阶段 - 从数据库读取结构信息
 */
public interface IPhaseScanDatabase
{
	MetaScheme scan(Connection conn, String scheme) throws SQLException;
}
