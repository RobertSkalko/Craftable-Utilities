package com.robertx22.craftable_utilities.mixins;

import com.robertx22.craftable_utilities.main.CommonInit;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerMixin {

    // server player overrides ondeath method, so we need thid specific mixin
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void myhookOnDeath(DamageSource source, CallbackInfo ci) {
        ServerPlayerEntity victim = (ServerPlayerEntity) (Object) this;
        CommonInit.PLAYER_DEATH_EVENT.callEvents(new ExileEvents.OnMobDeath(victim));
    }

}
