package firok.spring.msync.phase;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * 执行各类操作
 */
public class PhaseExecuteUpdate implements IPhaseExecuteUpdate
{
	public PhaseExecuteUpdate()
	{
		;
	}

	@Override
	public List<Object> execute(List<PreparedStatement> stmt)
	{
		return null;
	}
}
