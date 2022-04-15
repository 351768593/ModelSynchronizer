package firok.spring.msync.identifier;

/**
 * 将单词数组组合为标识符
 */
public interface IIdentifierCombiner
{
	/**
	 * @param arrayWord 单词数组
	 * @return 标识符
	 */
	String combine(String[] arrayWord);
}
