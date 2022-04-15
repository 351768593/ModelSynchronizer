package firok.spring.msync.phase;

import firok.spring.msync.bean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比结构间差异
 */
public class PhaseCompare implements IPhaseCompare
{
	/**
	 * @param schemeBean 实体结构信息
	 * @param schemeDatabase 数据库结构信息
	 * @return 库间差异
	 */
	@Override
	public List<AbstractDiff> compareScheme(MetaScheme schemeBean, MetaScheme schemeDatabase)
	{
		var ret = new ArrayList<AbstractDiff>();

		var crtTableName = schemeBean.compareTableName(schemeDatabase);
		// 只在实体的表
		crtTableName.getSetOnlyLeft()
				.stream().parallel()
				.forEach(nameTable -> {
					var table = schemeBean.getTable(nameTable);
					var diff = new DiffTableOnlyInBean(table);
					synchronized (ret) { ret.add(diff); }
				});
		// 只在数据库的表
		crtTableName.getSetOnlyRight()
				.stream().parallel()
				.forEach(nameTable -> {
					var table = schemeDatabase.getTable(nameTable);
					var diff = new DiffTableOnlyInDatabase(table);
					synchronized (ret) { ret.add(diff); }
				});
		// 实体和数据库都有的表
		crtTableName.getSetBoth()
				.stream().parallel()
				.forEach(nameTable -> {
					var tableBean = schemeBean.getTable(nameTable);
					var tableDatabase = schemeDatabase.getTable(nameTable);
					// 对比获得这些表之间的字段差异
					var listDiff = compareTable(tableBean,tableDatabase);
					if(!listDiff.isEmpty()) synchronized (ret) { ret.addAll(listDiff); }
				});
		return ret;
	}

	/**
	 * @return 表间差异
	 */
	public List<AbstractDiff> compareTable(MetaTable tableBean, MetaTable tableDatabase)
	{
		var ret = new ArrayList<AbstractDiff>();
		var crtFieldName = tableBean.compareTableName(tableDatabase);
		// 只在实体的字段
		crtFieldName.getSetOnlyLeft()
				.stream().parallel()
				.forEach(nameField -> {
					var field = tableBean.getField(nameField);
					var diff = new DiffFieldOnlyInBean(tableBean, field);
					synchronized (ret) { ret.add(diff); }
				});
		// 只在数据库的字段
		crtFieldName.getSetOnlyRight()
				.stream().parallel()
				.forEach(nameField -> {
					var field = tableDatabase.getField(nameField);
					var diff = new DiffFieldOnlyInDatabase(tableDatabase, field);
					synchronized (ret) { ret.add(diff); }
				});
		// 同时出现于实体和数据库的字段
		crtFieldName.getSetBoth()
				.stream().parallel()
				.forEach(nameField -> {
					var fieldBean = tableBean.getField(nameField);
					var fieldDatabase = tableDatabase.getField(nameField);
					// 对比两个字段之间的差异
					var diffField = compareField(fieldBean, fieldDatabase);
					if(diffField != null) synchronized (ret) { ret.add(diffField); }
				});
		return ret;
	}

	/**
	 * @return 字段间差异. 如果没有差异则返回 null
	 */
	public AbstractDiff compareField(MetaField fieldBean, MetaField fieldDatabase)
	{
		return fieldBean.name().equals(fieldDatabase.name()) &&
				fieldBean.type() == fieldDatabase.type() &&
				fieldBean.length() == fieldDatabase.length() ?
				null :
				new DiffFieldConflict(fieldBean, fieldDatabase);
	}
}
