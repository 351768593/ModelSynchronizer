package firok.spring.msync.util;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * i18n 工具
 */
public final class I18N
{
	private I18N() { }

	public static String localize(String key, Object... params)
	{
		var bundle = ResourceBundle.getBundle("msg");
		try
		{
			var template = bundle.getString(key);
			return template.formatted(params);
		}
		catch (Exception e)
		{
			return key + ": " + Arrays.toString(params);
		}
	}
}
