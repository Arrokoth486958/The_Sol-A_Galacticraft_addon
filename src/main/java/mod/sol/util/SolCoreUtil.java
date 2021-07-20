package mod.sol.util;

import net.minecraft.util.text.translation.I18n;

public class SolCoreUtil {
	public static String translate(String key)
    {
        String result = I18n.translateToLocal(key);
        int comment = result.indexOf('#');
        String ret = (comment > 0) ? result.substring(0, comment).trim() : result;
        for (int i = 0; i < key.length(); ++i)
        {
            Character c = key.charAt(i);
            if (Character.isUpperCase(c))
            {
                System.err.println(ret);
            }
        }
        return ret;
    }
}
