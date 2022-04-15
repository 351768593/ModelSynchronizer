package firok.spring.msync.util;

import java.util.Collection;

public interface Comparor<TypeElement>
{
	CompareResultTypeEnum compare(Collection<TypeElement> colLeft, Collection<TypeElement> colRight, TypeElement element);
}
