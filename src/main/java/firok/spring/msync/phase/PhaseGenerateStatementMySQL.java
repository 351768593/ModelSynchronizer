package firok.spring.msync.phase;

import firok.spring.msync.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
public class PhaseGenerateStatementMySQL implements IPhaseGenerateStatement
{
	@Override
	public List<PreparedStatement> parse(Connection conn, List<AbstractDiff> listDiff) throws SQLException
	{
		var ret = new ArrayList<PreparedStatement>(listDiff.size());
		for(var diff : listDiff)
		{
			ret.add(parseOne(conn, diff));
		}
		return ret;
	}

	public PreparedStatement parseOne(Connection conn, AbstractDiff ad) throws SQLException
	{
		// gossip all we are waiting for the pattern matching in java 18
		if(ad instanceof DiffFieldOnlyInDatabase diff) // 字段只存在于数据库
		{
			// 删除字段
			var ret = conn.prepareStatement("alter table ? drop column ?");
			ret.setString(1, diff.nameTable());
			ret.setString(2, diff.nameField());
			return ret;
		}
		else if(ad instanceof DiffFieldOnlyInBean diff) // 字段只存在于实体
		{
			// 创建字段
			var ret = conn.prepareStatement("alter table ? add column ?");
			ret.setString(1, diff.nameTable());
			ret.setString(2, diff.nameField() + " " + diff.getMetaField().toDefString());
			return ret;
		}
		else if(ad instanceof DiffFieldConflict diff) // 字段冲突
		{
			// 修改字段
			var ret = conn.prepareStatement("alter table ? modify column ?");
			ret.setString(1, diff.nameTable());
			ret.setString(2, diff.nameField() + " " + diff.getMetaFieldOther().toDefString());
			return ret;
		}
		else if(ad instanceof DiffTableOnlyInBean diff) // 表只存在于实体
		{
			// 创建表
		}
		else if(ad instanceof DiffTableOnlyInDatabase diff) // 表只存在于数据库
		{
			// 删除表
			var ret = conn.prepareStatement("drop table ?");
			ret.setString(1, diff.nameTable());
			return ret;
		}
		else if(ad instanceof DiffTableConflict diff) // 表冲突
		{
			// 修改表
		}

		return null;
	}
}
