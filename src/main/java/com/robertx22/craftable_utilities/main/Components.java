package com.robertx22.craftable_utilities.main;

import com.robertx22.craftable_utilities.EntityData;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class Components {

    public static Components INSTANCE;

    public ComponentType<EntityData> ENTITY_DATA =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.ID, "entity_data"),
            EntityData.class)
            .attach(EntityComponentCallback.event(LivingEntity.class), x -> new EntityData());

    public Components() {
        EntityComponents.setRespawnCopyStrategy(ENTITY_DATA, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
