package firok.spring.msync.identifier;

/**
 * 标识符组装器 - 大写下划线
 */
public enum IC_UpperUnderline implements IIdentifierCombiner
{
	INSTANCE;


	@Override
	public String combine(String[] arrayWord)
	{
		var arrayTemp = new String[arrayWord.length];
		for (int step = 0; step < arrayTemp.length; step ++)
		{
			arrayTemp[step] = arrayWord[step].toUpperCase();
		}

		return String.join("_", arrayTemp);
	}
}
