package firok.spring.msync.phase;

import firok.spring.msync.util.I18N;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingleConnectionMySQL implements ConnectionManager
{
	private final Connection conn;
	private final String scheme;
	public SingleConnectionMySQL(String url, String scheme, String username, String password) throws ClassNotFoundException, SQLException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (Exception e)
		{
			throw new ClassNotFoundException(I18N.localize("exception-load-driver-mysql"), e);
		}

		try
		{
			conn = DriverManager.getConnection(url, username, password);
			this.scheme = scheme;
		}
		catch (SQLException e)
		{
			throw new SQLException(I18N.localize("exception-open-conn"), e);
		}
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return conn;
	}

	@Override
	public String getScheme()
	{
		return scheme;
	}

//	@SneakyThrows
//	@Override
//	public PreparedStatement getStatement(String sql)
//	{
//		return conn.prepareStatement(sql);
//	}

	@SneakyThrows
	@Override
	public void close()
	{

		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			throw new IOException(I18N.localize("exception-close-conn"), e);
		}
	}
}
