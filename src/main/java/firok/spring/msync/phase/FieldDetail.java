package firok.spring.msync.phase;

import firok.spring.msync.util.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

/**
 * 用于在实体上标注表字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldDetail
{
	/**
	 * 对应的表字段名称
	 * 如果保留默认值, 将使用大驼峰转小写下划线
	 */
	String name() default Constants.DEFAULT;

	/**
	 * 对应的表字段类型
	 */
	JDBCType type() default JDBCType.OTHER;

	/**
	 * 字段默认值
	 */
	String columnDefault() default Constants.NULL;

	/**
	 * 字段长度
	 */
	int length() default 0;

	/**
	 * 字段精度
	 */
	int scale() default 0;

	/**
	 * 是否可空
	 */
	boolean isNullable() default true;

	/**
	 * 是否唯一
	 */
	boolean isUnique() default false;

	/**
	 * 是否主键
	 */
	boolean isPrimaryKey() default false;

	/**
	 * 是否自增
	 */
	boolean isAutoIncrement() default false;

	/**
	 * 索引名称. 默认无索引
	 */
	String nameIndex() default Constants.NULL;

	/**
	 * 索引模式
	 */
	String methodIndex() default Constants.DEFAULT;
}
