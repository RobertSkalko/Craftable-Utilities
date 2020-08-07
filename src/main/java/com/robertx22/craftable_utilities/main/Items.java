package com.robertx22.craftable_utilities.main;

import com.robertx22.craftable_utilities.EntityData;
import com.robertx22.craftable_utilities.items.ConsumableItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Items {

    public static Items INSTANCE;

    public Item SPAWN_TELEPORT = Registry.register(Registry.ITEM, new Identifier(Ref.ID, "spawn_teleport"), new ConsumableItem() {
        @Override
        public ItemStack onDoneUsing(ItemStack stack, World world, ServerPlayerEntity user) {

            BlockPos spawn = user.getSpawnPointPosition();

            if (spawn != null && user.getSpawnPointDimension()
                .getValue()
                .equals(world.getRegistryKey()
                    .getValue())) {
                stack.decrement(1);
                user.teleport(spawn.getX(), spawn.getY(), spawn.getZ());
            }

            return stack;
        }
    });

    public Item DEATH_TELEPORT = Registry.register(Registry.ITEM, new Identifier(Ref.ID, "death_teleport"), new ConsumableItem() {
        @Override
        public ItemStack onDoneUsing(ItemStack stack, World world, ServerPlayerEntity user) {
            EntityData data = Components.INSTANCE.ENTITY_DATA.get(user);

            if (data.pos != null && data.dim != null && data.dim
                .equals(world.getRegistryKey()
                    .getValue()
                    .toString())) {
                stack.decrement(1);
                user.teleport(data.pos.getX(), data.pos.getY(), data.pos.getZ());
            }

            return stack;
        }
    });

    public Item OBSIDIAN_CONSUMER = Registry.register(Registry.ITEM, new Identifier(Ref.ID, "obsidian_consumer"), new Item(new Item.Settings().maxCount(64)
        .group(ItemGroup.MISC)) {
        @Override
        public ActionResult useOnBlock(ItemUsageContext ctx) {
            if (ctx.getWorld()
                .getBlockState(ctx.getBlockPos())
                .getBlock() == Blocks.OBSIDIAN) {

                ctx.getWorld()
                    .setBlockState(ctx.getBlockPos(), Blocks.AIR.getDefaultState());

                ctx.getPlayer()
                    .dropItem(new ItemStack(net.minecraft.item.Items.OBSIDIAN), true);

                ctx.getPlayer()
                    .getStackInHand(ctx.getHand())
                    .decrement(1);

            }

            return ActionResult.SUCCESS;
        }

    });

}
