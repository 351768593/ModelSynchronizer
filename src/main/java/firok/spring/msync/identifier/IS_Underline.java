package firok.spring.msync.identifier;

import java.util.ArrayList;

/**
 * 将下划线标识符拆分为单词数组
 */
public enum IS_Underline implements IIdentifierSplitter
{
	INSTANCE;

	@Override
	public String[] split(String identifier)
	{
		if(identifier.length() < 2) return new String[] { identifier };

		char[] arrayChar = identifier.toCharArray();
		var listWord = new ArrayList<String>();
		int lastIndex = 0;
		for (int currentIndex = 1; currentIndex < arrayChar.length; currentIndex ++)
		{
			if(arrayChar[currentIndex] == '_')
			{
				var word = identifier.substring(lastIndex, currentIndex);
				listWord.add(word);
				lastIndex = currentIndex;
			}
		}
		return listWord.toArray(new String[0]);
	}
}
