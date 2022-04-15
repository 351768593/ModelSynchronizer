package firok.spring.msync.identifier;

import java.util.ArrayList;

/**
 * 将驼峰标识符拆分为单词数组
 */
public enum IS_Camel implements IIdentifierSplitter
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
			if(Character.isUpperCase(arrayChar[currentIndex]))
			{
				var word = identifier.substring(lastIndex, currentIndex);
				listWord.add(word);
				lastIndex = currentIndex;
			}
		}
		return listWord.toArray(new String[0]);
	}
}
