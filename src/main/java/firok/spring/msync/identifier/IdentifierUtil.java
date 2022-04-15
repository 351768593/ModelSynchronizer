package firok.spring.msync.identifier;

import firok.spring.msync.util.I18N;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理标识符相关代码
 */
public final class IdentifierUtil
{
	private IdentifierUtil() { }

	private static final Map<Class<? extends IIdentifierSplitter>, IIdentifierSplitter> mapSpitter = new HashMap<>();
	private static final Map<Class<? extends IIdentifierCombiner>, IIdentifierCombiner> mapCombiner = new HashMap<>();
	static
	{
		mapSpitter.put(IS_Camel.class, IS_Camel.INSTANCE);
		mapSpitter.put(IS_Underline.class, IS_Underline.INSTANCE);

		mapCombiner.put(IC_LowerCamel.class, IC_LowerCamel.INSTANCE);
		mapCombiner.put(IC_UpperCamel.class, IC_UpperCamel.INSTANCE);
		mapCombiner.put(IC_LowerUnderline.class, IC_LowerUnderline.INSTANCE);
		mapCombiner.put(IC_UpperUnderline.class, IC_UpperUnderline.INSTANCE);
	}

	private static <T> T newInstance(Class<T> classSomething, String exceptionKey) throws IllegalArgumentException
	{
		try
		{
			var cons = classSomething.getConstructor();
			return cons.newInstance();
		}
		catch (Throwable e)
		{
			throw new IllegalArgumentException(I18N.localize(exceptionKey), e);
		}
	}

	/**
	 * 获取一个标识符分割器实例
	 * @throws IllegalArgumentException 如果指定标识符分割器类无法实例化则抛出
	 */
	public static IIdentifierSplitter ofSplitter(Class<? extends IIdentifierSplitter> classSplitter) throws IllegalArgumentException
	{
		synchronized (mapSpitter)
		{
			var ret = mapSpitter.get(classSplitter);
			if(ret == null)
			{
				ret = newInstance(classSplitter, "exception-instancing-splitter");
				mapSpitter.put(classSplitter, ret);
			}
			return ret;
		}
	}

	/**
	 * 获取一个标识符组装器实例
	 * @throws IllegalArgumentException 如果指定标识符组装器类无法实例化则抛出
	 */
	public static IIdentifierCombiner ofCombiner(Class<? extends IIdentifierCombiner> classCombiner)
	{
		synchronized (mapCombiner)
		{
			var ret = mapCombiner.get(classCombiner);
			if(ret == null)
			{
				ret = newInstance(classCombiner, "exception-instancing-combiner");
				mapCombiner.put(classCombiner, ret);
			}
			return ret;
		}
	}

	/**
	 * 字符串首字母大写
	 *
	 * @implNote https://blog.csdn.net/qq_37949192/article/details/117415225
	 */
	static String toUpperFirst(String word)
	{
		var arrayChar = word.toCharArray();
		char firstChar = arrayChar[0];
		if (97 <= firstChar && firstChar <= 122)
		{
			firstChar -= 32;
		}
		arrayChar[0] = firstChar;
		return String.valueOf(arrayChar);
	}
}
