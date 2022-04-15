package firok.spring.msync;

import firok.spring.msync.bean.MetaScheme;
import firok.spring.msync.phase.*;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 需要在使用结束后调用 {@code #close() } 释放资源.
 */
public class ModelSynchronizer implements Closeable
{
	final ConnectionManager connMgr;
	final IPhaseScanModel phaseScanModel;
	final IPhaseScanDatabase phaseScanDatabase;
	final IPhaseCompare phaseCompare;
	final IPhaseGenerateStatement phaseGenerateStatement;
	final IPhaseExecuteUpdate phaseExecuteUpdate;
	final long timeout;
	public ModelSynchronizer(
			ConnectionManager connMgr,
			IPhaseScanModel phaseScanModel,
			IPhaseScanDatabase phaseScanDatabase,
			IPhaseCompare phaseCompare,
			IPhaseGenerateStatement phaseGenerateStatement,
			IPhaseExecuteUpdate phaseExecuteUpdate
	)
	{
		this(
			connMgr,
			phaseScanModel,
			phaseScanDatabase,
			phaseCompare,
			phaseGenerateStatement,
			phaseExecuteUpdate,
			10000
		);
	}

	/**
	 * @param timeout 最大执行时间, 单位ms
	 * @implNote 目前底层实现为同步模式, {@code timeout } 参数不生效. 将来可能改为多线程模式
	 */
	public ModelSynchronizer(
			ConnectionManager connMgr,
			IPhaseScanModel phaseScanModel,
			IPhaseScanDatabase phaseScanDatabase,
			IPhaseCompare phaseCompare,
			IPhaseGenerateStatement phaseGenerateStatement,
			IPhaseExecuteUpdate phaseExecuteUpdate,
			long timeout
	)
	{
		this.connMgr = connMgr;
		this.phaseScanModel = phaseScanModel;
		this.phaseScanDatabase = phaseScanDatabase;
		this.phaseCompare = phaseCompare;
		this.phaseGenerateStatement = phaseGenerateStatement;
		this.phaseExecuteUpdate = phaseExecuteUpdate;
		this.timeout = timeout;
	}

// todo low 改为多线程实现
//	private class Mid
//	{
//		final Object LOCK = new Object();
//		MetaScheme schemeBean = null;
//		MetaScheme schemeDatabase = null;
//	}
	public void execute()
	{

		try
		{
			var conn = this.connMgr.getConnection();
			var nameScheme = this.connMgr.getScheme();
			var schemeBean = this.phaseScanModel.scan();
			var schemeDatabase = this.phaseScanDatabase.scan(conn, nameScheme);
			var listDiff = this.phaseCompare.compareScheme(schemeBean, schemeDatabase);
			var listStmt = this.phaseGenerateStatement.parse(conn, listDiff);
			var listResult = this.phaseExecuteUpdate.execute(listStmt);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("EmptyTryBlock")
	private void tryClose(Object obj)
	{
		if(obj instanceof Closeable closeable)
		{
			try(closeable) { } catch (IOException ignored) { }
		}
	}
	@Override
	public void close()
	{
		tryClose(connMgr);
		tryClose(phaseScanModel);
		tryClose(phaseScanDatabase);
		tryClose(phaseCompare);
		tryClose(phaseGenerateStatement);
		tryClose(phaseExecuteUpdate);
	}

	public static void main(String[] args) throws Exception
	{
		DefaultImplementations.sync_MVCI_MySQL(
				"jdbc:mysql://localhost:3306/?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull",
				"blogy", "root", "root");
	}
}
