package mod.sol.config;

import java.io.File;

import mod.sol.TheSol;
import mod.sol.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Config(modid = Reference.MOD_ID, name = Reference.NAME)
public class ConfigManagerSol {
	private static Configuration config;

	public static int disableDynamicTierSystem;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		config.addCustomCategoryComment("The Sol", "Config");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		TheSol.configSol = new File(event.getModConfigurationDirectory() + "/" + Reference.MOD_ID);
		TheSol.configSol.mkdirs();
		init(new File(TheSol.configSol.getPath(), Reference.MOD_ID + ".conf"));
	}
}
