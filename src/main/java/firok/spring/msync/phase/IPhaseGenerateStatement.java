package firok.spring.msync.phase;

import firok.spring.msync.bean.AbstractDiff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 阶段 - 根据结构差异, 生成SQL语句
 */
public interface IPhaseGenerateStatement
{
	List<PreparedStatement> parse(Connection conn, List<AbstractDiff> listDiff) throws SQLException;
}
