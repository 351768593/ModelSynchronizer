package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;

/**
 * 差异 - 表只于数据库存在
 */
@SuppressWarnings("SqlNoDataSourceInspection")
public class DiffTableOnlyInDatabase extends AbstractTableDiff
{
	public DiffTableOnlyInDatabase(MetaTable metaTable)
	{
		super(metaTable);
	}

	@Override
	public String toString()
	{
		String nameTable = nameTable();
		return I18N.localize("diff-table-bean", nameTable);
	}
}
