package firok.spring.msync.phase;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * 阶段 - 执行所有SQL语句
 */
public interface IPhaseExecuteUpdate
{
	List<Object> execute(List<PreparedStatement> stmt);
}
