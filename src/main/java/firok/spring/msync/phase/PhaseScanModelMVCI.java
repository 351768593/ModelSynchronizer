package firok.spring.msync.phase;

import firok.spring.msync.bean.MetaScheme;
import firok.spring.msync.identifier.*;

/**
 * 基于MVCI和自定义注解扫描实体
 * @see firok.spring.mvci.runtime.CurrentBeanClasses
 * @see firok.spring.msync.phase.FieldDetail
 */
public class PhaseScanModelMVCI implements IPhaseScanModel
{
	protected IIdentifierSplitter identifierSplitter;
	protected IIdentifierCombiner identifierCombiner;
	public PhaseScanModelMVCI()
	{
		this(
				IdentifierUtil.ofSplitter(IS_Camel.class),
				IdentifierUtil.ofCombiner(IC_LowerUnderline.class)
		);
	}
	public PhaseScanModelMVCI(
			IIdentifierSplitter identifierSplitter,
			IIdentifierCombiner identifierCombiner
	)
	{
		this.identifierSplitter = identifierSplitter;
		this.identifierCombiner = identifierCombiner;
	}

	@Override
	public MetaScheme scan()
	{
		var ret = new MetaScheme("");


		return ret;
	}
}
