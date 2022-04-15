package firok.spring.msync.bean;

import lombok.Getter;

import java.util.Objects;

/**
 * 与字段有关的差异
 */
public abstract class AbstractFieldDiff extends AbstractTableDiff
{
	@Getter
	protected final MetaField metaField;

	public String nameField()
	{
		return metaField.name();
	}

	public AbstractFieldDiff(MetaTable metaTable, MetaField metaField)
	{
		super(metaTable);
		this.metaField = Objects.requireNonNull(metaField);
	}
}
