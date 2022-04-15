package firok.spring.msync.bean;

import firok.spring.msync.util.I18N;

/**
 * 差异 - 字段只于数据库存在
 */
@SuppressWarnings("SqlNoDataSourceInspection")
public class DiffFieldOnlyInDatabase extends AbstractFieldDiff
{
	public DiffFieldOnlyInDatabase(MetaTable metaTable, MetaField metaField)
	{
		super(metaTable, metaField);
	}

	@Override
	public String toString()
	{
		String nameTable, nameField;
		nameTable = nameTable();
		nameField = nameField();
		return I18N.localize("diff-field-bean", nameTable, nameField);
	}
}
