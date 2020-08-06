package com.robertx22.craftable_utilities;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;

public class EntityData implements Component {

    public BlockPos pos;
    public String dim;

    @Override
    public void fromTag(CompoundTag nbt) {
        this.pos = BlockPos.fromLong(nbt.getLong("death_pos"));
        this.dim = nbt.getString("dim");
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        if (pos != null) {
            nbt.putLong("death_pos", pos.asLong());
        }
        if (dim != null) {
            nbt.putString("dim", dim);
        }
        return nbt;
    }
}
