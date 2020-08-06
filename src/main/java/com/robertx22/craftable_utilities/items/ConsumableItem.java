package com.robertx22.craftable_utilities.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public abstract class ConsumableItem extends Item {

    public ConsumableItem() {
        super(new Settings().maxCount(16));
    }

    public abstract ItemStack onDoneUsing(ItemStack stack, World world, ServerPlayerEntity user);

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            return onDoneUsing(stack, world, (ServerPlayerEntity) user);
        }
        return stack;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        for (int i = 0; i < 5; i++) {
            world.addParticle(ParticleTypes.REVERSE_PORTAL,
                user.getX() - 0.5F + world.random.nextDouble(),
                user.getY() + user.getHeight() / 2,
                user.getZ() - 0.5F + world.random.nextDouble()
                , 0, 0, 0);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 50;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
