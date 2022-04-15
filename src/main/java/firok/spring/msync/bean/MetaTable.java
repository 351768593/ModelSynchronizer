package firok.spring.msync.bean;

import firok.spring.msync.util.CompareResult;
import firok.spring.msync.util.CompareUtil;
import lombok.Getter;

import java.util.*;

public class MetaTable
{
	@Getter
	private final MetaScheme metaScheme;
	@Getter
	private final String name;

	private final Set<String> setFieldName;
	private final Map<String, MetaField> mapField;

	public MetaTable(MetaScheme metaScheme, String name)
	{
		this.metaScheme = Objects.requireNonNull(metaScheme);
		this.setFieldName = new HashSet<>();
		this.mapField = new HashMap<>();
		this.name = Objects.requireNonNull(name);
	}

	/**
	 * 对比内部字段名列表
	 */
	public CompareResult<String> compareTableName(MetaTable other)
	{
		return CompareUtil.compare(this.setFieldName, other.setFieldName);
	}

	public void addField(MetaField field)
	{
		var nameField = field.name();
		this.setFieldName.add(nameField);
		this.mapField.put(nameField, field);
	}
	public void removeField(String nameField)
	{
		this.setFieldName.remove(nameField);
		this.mapField.remove(nameField);
	}
	public MetaField getField(String nameField)
	{
		return this.mapField.get(nameField);
	}
}
