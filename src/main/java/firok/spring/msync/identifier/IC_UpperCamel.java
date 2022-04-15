package firok.spring.msync.identifier;

/**
 * 标识符组装器 - 大驼峰
 */
public enum IC_UpperCamel implements IIdentifierCombiner
{
	INSTANCE;

	@Override
	public String combine(String[] arrayWord)
	{
		var arrayTemp = new String[arrayWord.length];
		for (int step = 0; step < arrayTemp.length; step ++)
		{
			var tempWord = arrayWord[step].toLowerCase();
			arrayTemp[step] = IdentifierUtil.toUpperFirst(tempWord);
		}

		return String.join("", arrayTemp);
	}
}
