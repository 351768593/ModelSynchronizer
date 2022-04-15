package firok.spring.msync.bean;

import lombok.Getter;

import java.util.Objects;

/**
 * 与表有关的差异
 */
public abstract class AbstractTableDiff extends AbstractDiff
{
	@Getter
	protected final MetaTable metaTable;

	public String nameTable()
	{
		return metaTable.getName();
	}

	public AbstractTableDiff(MetaTable metaTable)
	{
		this.metaTable = Objects.requireNonNull(metaTable);
	}
}
