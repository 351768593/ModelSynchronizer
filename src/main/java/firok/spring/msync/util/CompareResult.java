package firok.spring.msync.util;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CompareResult<TypeElement>
{
	public CompareResult()
	{
		this.setOnlyLeft = new HashSet<>();
		this.setOnlyRight = new HashSet<>();
		this.setBoth = new HashSet<>();
		this.setConflict = new HashSet<>();
	}

	Set<TypeElement> setOnlyLeft, setOnlyRight, setBoth, setConflict;

	@SuppressWarnings("UnnecessaryDefault")
	public Set<TypeElement> list(CompareResultTypeEnum type)
	{
		return switch (type)
		{
			case OnlyInLeft -> setOnlyLeft;
			case OnlyInRight -> setOnlyRight;
			case InBoth -> setBoth;
			case Conflict -> setConflict;
			default -> new HashSet<>();
		};
	}
}
