package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;
import lombok.Getter;

import java.util.Objects;

/**
 * 差异 - 字段属性冲突
 */
public class DiffFieldConflict extends AbstractFieldDiff
{
	@Getter
	protected final MetaField metaFieldOther;
	public DiffFieldConflict(MetaField metaField, MetaField metaFieldOther)
	{
		super(metaField.metaTable(), metaField);
		this.metaFieldOther = Objects.requireNonNull(metaFieldOther);
	}

	public String nameTableOther()
	{
		return metaFieldOther.metaTable().getName();
	}

	public String nameFieldOther()
	{
		return metaFieldOther.name();
	}

	@Override
	public String toString()
	{
		String nameTable, nameField, nameTable2, nameField2;
		nameField = nameField();
		nameTable = nameTable();
		nameField2 = nameFieldOther();
		nameTable2 = nameTableOther();
		return I18N.localize("diff-field-conflict", nameTable, nameField, nameTable2, nameField2);
	}
}
