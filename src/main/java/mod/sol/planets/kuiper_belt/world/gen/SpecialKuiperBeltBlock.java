package mod.sol.planets.kuiper_belt.world.gen;

import java.util.ArrayList;
import net.minecraft.block.Block;

public class SpecialKuiperBeltBlock {
    public Block block;
    public byte meta;
    public int probability;
    public double thickness;
    public int index;
    public static ArrayList<SpecialKuiperBeltBlock> register = new ArrayList();

    public SpecialKuiperBeltBlock(Block block, byte meta, int probability, double thickness) {
        this.block = block;
        this.meta = meta;
        this.probability = probability;
        this.thickness = thickness;
        this.index = register.size();
        register.add(this);
    }
}
