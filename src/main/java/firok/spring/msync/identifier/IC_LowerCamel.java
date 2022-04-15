package firok.spring.msync.identifier;

/**
 * 标识符组装器 - 小驼峰
 */
public enum IC_LowerCamel implements IIdentifierCombiner
{
	INSTANCE;

	@Override
	public String combine(String[] arrayWord)
	{
		var arrayTemp = new String[arrayWord.length];
		for (int step = 0; step < arrayTemp.length; step ++)
		{
			var tempWord = arrayWord[step].toLowerCase();
			arrayTemp[step] = step != 0 ?
					IdentifierUtil.toUpperFirst(tempWord) :
					tempWord;
		}

		return String.join("", arrayTemp);
	}
}
