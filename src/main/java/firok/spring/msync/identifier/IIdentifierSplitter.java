package firok.spring.msync.identifier;

/**
 * 将标识符处理为单词数组
 */
public interface IIdentifierSplitter
{
	/**
	 * @param identifier 标识符
	 * @return 处理之后的单词数组
	 */
	String[] split(String identifier);
}
