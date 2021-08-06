package mod.sol.api.galaxy;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;

public class GasGiant extends Planet {
    public GasGiant(String planetName) {
        super(planetName);
        //Well it's just let the Galaxymap know that it don't have to render "side bar" for this body
    }
}
