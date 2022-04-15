package firok.spring.msync.phase;

import firok.spring.msync.ModelSynchronizer;

import java.sql.SQLException;

public final class DefaultImplementations
{
	private DefaultImplementations() { }

	/**
	 * @param url 数据库URL
	 * @param scheme 数据库scheme
	 * @param username 数据库用户名
	 * @param password 数据库密码
	 * @see PhaseScanModelMVCI
	 * @see PhaseScanDatabaseMySQL
	 * @see PhaseGenerateStatementMySQL
	 */
	public static void sync_MVCI_MySQL(
			String url,
			String scheme,
			String username,
			String password
	) throws SQLException, ClassNotFoundException
	{
		var connMgr = new SingleConnectionMySQL(url, scheme, username, password);
		var phaseScanModel = new PhaseScanModelMVCI();
		var phaseScanDatabase = new PhaseScanDatabaseMySQL();
		var phaseCompare = new PhaseCompare();
		var phaseGenerateStatement = new PhaseGenerateStatementMySQL();
		var phaseExecuteUpdate = new PhaseExecuteUpdate();
		var msync = new ModelSynchronizer(connMgr, phaseScanModel, phaseScanDatabase, phaseCompare, phaseGenerateStatement, phaseExecuteUpdate, 10000);
		msync.execute();
	}
}
