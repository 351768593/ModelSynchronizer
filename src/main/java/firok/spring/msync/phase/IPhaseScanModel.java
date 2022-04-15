package firok.spring.msync.phase;

import firok.spring.msync.bean.MetaScheme;

/**
 * 阶段 - 从模型读取结构信息
 */
public interface IPhaseScanModel
{
	MetaScheme scan();
}
