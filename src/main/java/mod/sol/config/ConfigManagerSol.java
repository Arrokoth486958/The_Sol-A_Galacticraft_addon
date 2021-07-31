package mod.sol.config;

import mod.sol.TheSol;
import mod.sol.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

//@Config(modid = Reference.MOD_ID, name = Reference.MOD_ID)
public class ConfigManagerSol {
    public static Configuration config;

    public static int dimensionidMercury = -(Reference.MOD_ID.hashCode() + 100);

    public static void init(File file) {
        config = new Configuration(file);
        String categoryDimensionids = "The Sol - Dimension IDs";
        config.addCustomCategoryComment(categoryDimensionids, "IDs for dimensions of the mod 'The Sol'");
        dimensionidMercury = config.getInt("Dimension ID for Mercury", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 100), -999999999, 999999999, "None");
        config.save();
    }

    public static void  registerConfig(FMLPreInitializationEvent event) {
        TheSol.configSol = new File(event.getModConfigurationDirectory() + "/" + Reference.MOD_ID);
        TheSol.configSol.mkdirs();
        init(new File(TheSol.configSol.getPath(), Reference.MOD_ID + ".conf"));
    }
}
