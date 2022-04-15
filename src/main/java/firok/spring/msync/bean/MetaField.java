package firok.spring.msync.bean;

import java.sql.JDBCType;

public record MetaField(MetaTable metaTable, String name,
                        JDBCType type, int length)
{
	public String toDefString()
	{
		return name + " " + type.getName();
	}
}
