package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;

/**
 * 差异 - 表只于实体存在
 */
public class DiffTableOnlyInBean extends AbstractTableDiff
{
	public DiffTableOnlyInBean(MetaTable metaTable)
	{
		super(metaTable);
	}

	@Override
	public String toString()
	{
		String nameTable = nameTable();
		return I18N.localize("diff-table-database", nameTable);
	}
}
