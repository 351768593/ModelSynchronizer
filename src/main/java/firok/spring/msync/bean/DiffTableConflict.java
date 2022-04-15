package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;
import lombok.Getter;

import java.util.Objects;

/**
 * 差异 - 表属性冲突
 * @deprecated 目前暂时没有用到这个的
 */
@Deprecated
public class DiffTableConflict extends AbstractTableDiff
{
	@Getter
	private final MetaTable metaTableOther;
	public DiffTableConflict(MetaTable metaTable, MetaTable metaTableOther)
	{
		super(metaTable);
		this.metaTableOther = Objects.requireNonNull(metaTableOther);
	}

	public String nameTableOther()
	{
		return metaTableOther.getName();
	}

	@Override
	public String toString()
	{
		String nameTable, nameTable2;
		nameTable = nameTable();
		nameTable2 = nameTableOther();
		return I18N.localize("diff-table-conflict", nameTable, nameTable2);
	}
}
