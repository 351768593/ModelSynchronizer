package firok.spring.msync.phase;

import firok.spring.msync.bean.MetaField;
import firok.spring.msync.bean.MetaScheme;
import firok.spring.msync.bean.MetaTable;
import firok.spring.msync.util.I18N;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;

/**
 * 基于原生MySQL驱动, 读取数据库表结构信息
 * @implNote 这玩意是连MySQL8用的
 * @see com.mysql.cj.jdbc.Driver
 * @author Firok
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
public class PhaseScanDatabaseMySQL implements IPhaseScanDatabase
{
	@Override
	public MetaScheme scan(Connection conn, String scheme) throws SQLException
	{
		try
		{
			var stmt = conn.prepareStatement("""
select TABLE_NAME, COLUMN_NAME, COLUMN_DEFAULT, IS_NULLABLE,
       CHARACTER_MAXIMUM_LENGTH, NUMERIC_PRECISION, NUMERIC_SCALE, DATETIME_PRECISION,
       DATA_TYPE, COLUMN_KEY, EXTRA, GENERATION_EXPRESSION, COLUMN_COMMENT
from information_schema.COLUMNS
where TABLE_SCHEMA = ?
""");
			stmt.setString(1, scheme);

			var ret = new MetaScheme(scheme);

			System.out.println("read database structure: ");
			var rs = stmt.executeQuery();
			while(rs.next())
			{
				var TABLE_NAME = rs.getString("TABLE_NAME");
				var COLUMN_NAME = rs.getString("COLUMN_NAME");
				var IS_NULLABLE = rs.getBoolean("IS_NULLABLE");
				var DATA_TYPE = rs.getString("DATA_TYPE");
				var COLUMN_KEY = rs.getString("COLUMN_KEY");
				var CHARACTER_MAXIMUM_LENGTH = rs.getInt("CHARACTER_MAXIMUM_LENGTH");
				var NUMERIC_PRECISION = rs.getInt("NUMERIC_PRECISION");
				var NUMERIC_SCALE = rs.getInt("NUMERIC_SCALE");
				var DATETIME_PRECISION = rs.getInt("DATETIME_PRECISION");

				System.out.printf(
						"%s, %s, %s, %s, %s, %d, %d, %d, %d\n",
						TABLE_NAME, COLUMN_NAME, IS_NULLABLE, DATA_TYPE, COLUMN_KEY,
						CHARACTER_MAXIMUM_LENGTH, NUMERIC_PRECISION, NUMERIC_SCALE, DATETIME_PRECISION
				);

				var metaTable = ret.getTable(TABLE_NAME);
				if(metaTable == null)
				{
					metaTable = new MetaTable(ret, TABLE_NAME);
					ret.addTable(metaTable);
				}
				var metaField = metaTable.getField(COLUMN_NAME);
				if(metaField == null)
				{
					metaField = new MetaField(metaTable, COLUMN_NAME, JDBCType.OTHER, 0);
					metaTable.addField(metaField);
				}
			}
			System.out.println("end.");
			return ret;
		}
		catch (SQLException e)
		{
			throw new SQLException(I18N.localize("exception-scan-database"), e);
		}
	}
}
