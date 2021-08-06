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

    public static boolean enableCustomGalaxymap = true;

    public static int dimensionidMercury = -(Reference.MOD_ID.hashCode() + 100);
    public static int dimensionidIo = -(Reference.MOD_ID.hashCode() + 501);
    public static int dimensionidEuropa = -(Reference.MOD_ID.hashCode() + 502);
    public static int dimensionidMimas = -(Reference.MOD_ID.hashCode() + 601);
    public static int dimensionidTitan = -(Reference.MOD_ID.hashCode() + 606);
    public static int dimensionidAriel = -(Reference.MOD_ID.hashCode() + 701);
    public static int dimensionidPluto = -(Reference.MOD_ID.hashCode() + 900);
    public static int dimensionidKuiperBelt= -(Reference.MOD_ID.hashCode() + 1000);

    public static void init(File file) {
        config = new Configuration(file);
        String categoryDimensionids = "The Sol - Dimension IDs";
        String categoryMisc = "The Sol - Misc";
        config.addCustomCategoryComment(categoryDimensionids, "IDs for dimensions of the mod 'The Sol'");
        dimensionidMercury = config.getInt("Dimension ID for Mercury", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 100), -999999999, 999999999, "None");
        dimensionidIo = config.getInt("Dimension ID for Io", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 501), -999999999, 999999999, "None");
        dimensionidEuropa = config.getInt("Dimension ID for Europa", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 502), -999999999, 999999999, "None");
        dimensionidMimas = config.getInt("Dimension ID for Mimas", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 601), -999999999, 999999999, "None");
        dimensionidTitan = config.getInt("Dimension ID for Titan", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 606), -999999999, 999999999, "None");
        dimensionidAriel = config.getInt("Dimension ID for Ariel", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 701), -999999999, 999999999, "None");
        dimensionidPluto = config.getInt("Dimension ID for Pluto", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 900), -999999999, 999999999, "None");
        dimensionidPluto = config.getInt("Dimension ID for Kuiper Belt", categoryDimensionids, -(Reference.MOD_ID.hashCode() + 1000), -999999999, 999999999, "None");

        config.addCustomCategoryComment(categoryDimensionids, "IDs for dimensions of the mod 'The Sol'");
        enableCustomGalaxymap = config.getBoolean("Enable Custom Galaxymap?", categoryMisc, true, "If this config is true, it will override Galacticraft default Galaxymap");
        config.save();
    }

    public static void  registerConfig(FMLPreInitializationEvent event) {
        TheSol.configSol = new File(event.getModConfigurationDirectory() + "/" + Reference.MOD_ID);
        TheSol.configSol.mkdirs();
        init(new File(TheSol.configSol.getPath(), Reference.MOD_ID + ".conf"));
    }
}
