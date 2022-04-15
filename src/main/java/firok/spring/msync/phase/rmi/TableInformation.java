//package firok.spring.msync.phase.rmi;
//
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Data
//
//public class TableInformation
//{
//	String tableName;
//	Map<String, FieldInformation> mapField;
//
//	/**
//	 * 创建一份空的表信息
//	 */
//	public TableInformation()
//	{
//		this.mapField = new HashMap<>();
//	}
//
//	/**
//	 * 从实体类读取一份信息
//	 */
//	public TableInformation(Class<?> classBean)
//	{
//		this();
//
//		Class<?> classCurrent = classBean;
//		this.tableName = classCurrent.getName();
//		while (classCurrent != Object.class)
//		{
//			for (var objField : classCurrent.getDeclaredFields())
//			{
//				try
//				{
//					var objFieldInformation = new FieldInformation(objField);
//					this.mapField.put(objFieldInformation.getName(), objFieldInformation);
//				}
//				catch (FieldNotSuitableException ignored) { }
//			}
//
//			// 继续扫描父类字段
//			classCurrent = classCurrent.getSuperclass();
//		}
//	}
//}
