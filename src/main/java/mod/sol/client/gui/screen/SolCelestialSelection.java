package mod.sol.client.gui.screen;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import org.lwjgl.util.vector.Matrix4f;

import java.util.HashMap;
import java.util.List;

public class SolCelestialSelection extends GuiCelestialSelection {
    public SolCelestialSelection(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations) {
        super(mapMode, possibleBodies, canCreateStations);
    }
}
