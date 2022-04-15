package firok.spring.msync.bean;

import firok.spring.msync.util.CompareResult;
import firok.spring.msync.util.CompareUtil;
import lombok.Getter;

import java.util.*;

public class MetaScheme
{
	@Getter
	private final String name;

	private final Set<String> setTableName;
	private final Map<String, MetaTable> mapTable;

	public MetaScheme(String name)
	{
		this.setTableName = new HashSet<>();
		this.mapTable = new HashMap<>();
		this.name = name;
	}

	/**
	 * 对比内部表名列表
	 */
	public CompareResult<String> compareTableName(MetaScheme other)
	{
		return CompareUtil.compare(this.setTableName, other.setTableName);
	}

	public void addTable(MetaTable table)
	{
		var nameTable = table.getName();
		this.setTableName.add(nameTable);
		this.mapTable.put(nameTable, table);
	}
	public void removeTable(String nameTable)
	{
		this.setTableName.remove(nameTable);
		this.mapTable.remove(nameTable);
	}
	public MetaTable getTable(String nameTable)
	{
		return this.mapTable.get(nameTable);
	}

}
