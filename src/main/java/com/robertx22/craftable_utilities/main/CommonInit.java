package com.robertx22.craftable_utilities.main;

import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.network.ServerPlayerEntity;

public class CommonInit implements ModInitializer {

    @Override
    public void onInitialize() {
        Items.INSTANCE = new Items();
        Components.INSTANCE = new Components();

        ExileEvents.PLAYER_DEATH.register(new EventConsumer<ExileEvents.OnMobDeath>() {
            @Override
            public void accept(ExileEvents.OnMobDeath event) {
                if (event.mob instanceof ServerPlayerEntity) {
                    try {
                        Components.INSTANCE.ENTITY_DATA.get(event.mob).pos = event.mob.getBlockPos();
                        Components.INSTANCE.ENTITY_DATA.get(event.mob).dim = event.mob.world.getRegistryKey()
                            .getValue()
                            .toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.out.println("Craftable Utilities loaded.");
    }

}
