package mod.sol.planets.kuiper_belt.dimension;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class KuiperBeltSaveData extends WorldSavedData {
    public static final String saveDataID = "../sol/SolKuiperBeltData";
    public NBTTagCompound datacompound = new NBTTagCompound();

    public KuiperBeltSaveData(String s) {
        super("../sol/SolKuiperBeltData");
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.datacompound = nbt.getCompoundTag("kuiper_belt");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("kuiper_belt", this.datacompound);
        return nbt;
    }
}
