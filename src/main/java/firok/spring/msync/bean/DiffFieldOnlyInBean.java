package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;

/**
 * 差异 - 字段只于实体存在
 */
public class DiffFieldOnlyInBean extends AbstractFieldDiff
{
	public DiffFieldOnlyInBean(MetaTable metaTable, MetaField metaField)
	{
		super(metaTable, metaField);
	}

	@Override
	public String toString()
	{
		String nameTable, nameField;
		nameTable = nameTable();
		nameField = nameField();
		return I18N.localize("diff-field-database", nameTable, nameField);
	}
}
