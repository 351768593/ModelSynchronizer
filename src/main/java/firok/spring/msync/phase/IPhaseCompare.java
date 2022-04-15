package firok.spring.msync.phase;

import firok.spring.msync.bean.AbstractDiff;
import firok.spring.msync.bean.MetaScheme;

import java.util.List;

/**
 * 阶段 - 对比两侧结构, 获取差异列表
 */
public interface IPhaseCompare
{
	List<AbstractDiff> compareScheme(MetaScheme schemeBean, MetaScheme schemeDatabase);
}
