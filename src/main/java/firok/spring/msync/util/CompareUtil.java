package firok.spring.msync.util;

import io.vavr.collection.Set;

import java.util.Collection;
import java.util.HashSet;

public final class CompareUtil
{
	private CompareUtil() { }

	/**
	 * 使用默认比较器比较左右集合
	 * @param colLeft 左集合
	 * @param colRight 右集合
	 * @param <TypeElement> 集合元素类型
	 * @return 比较结果
	 */
	public static <TypeElement> CompareResult<TypeElement> compare(Collection<TypeElement> colLeft, Collection<TypeElement> colRight)
	{
		return compare(colLeft, colRight, CompareUtil::compareDefault);
	}

	/**
	 * 使用自定义比较器比较左右集合
	 */
	public static <TypeElement> CompareResult<TypeElement> compare(
			Collection<TypeElement> colLeft,
			Collection<TypeElement> colRight,
			Comparor<TypeElement> comparor
	)
	{
		var result = new CompareResult<TypeElement>();

		var setAll = new HashSet<TypeElement>();
		setAll.addAll(colLeft);
		setAll.addAll(colRight);


		for (var ele : setAll)
		{
			var crt = comparor.compare(colLeft, colRight, ele); // compare result type
			result.list(crt).add(ele);
		}

		return result;
	}

	public static <TypeElement> CompareResultTypeEnum compareDefault(Collection<TypeElement> colLeft, Collection<TypeElement> colRight, TypeElement element)
	{
		boolean inLeft = colLeft.contains(element);
		boolean inRight = colRight.contains(element);
		if (inLeft && inRight) return CompareResultTypeEnum.InBoth;
		else if (inLeft) return CompareResultTypeEnum.OnlyInLeft;
		else if (inRight) return CompareResultTypeEnum.OnlyInRight;
		else return CompareResultTypeEnum.Conflict;
	}
}
