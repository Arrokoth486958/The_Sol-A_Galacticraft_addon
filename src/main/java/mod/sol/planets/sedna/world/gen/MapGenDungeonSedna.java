package mod.sol.planets.sedna.world.gen;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class MapGenDungeonSedna extends MapGenDungeon
{
    private static boolean initialized;

    static
    {
        try
        {
            MapGenDungeonSedna.initiateStructures();
        }
        catch (Throwable e)
        {

        }
    }

    public MapGenDungeonSedna(DungeonConfiguration configuration)
    {
        super(configuration);
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenDungeonSedna.initialized)
        {
            MapGenStructureIO.registerStructureComponent(RoomBossSedna.class, "SednaDungeonBossRoom");
            MapGenStructureIO.registerStructureComponent(RoomTreasureSedna.class, "SednaDungeonTreasureRoom");
        }

        MapGenDungeonSedna.initialized = true;
    }
}
