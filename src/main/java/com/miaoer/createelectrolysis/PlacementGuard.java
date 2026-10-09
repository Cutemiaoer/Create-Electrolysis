package com.miaoer.createelectrolysis;

import com.miaoer.createelectrolysis.electrolyzer.ElectrolyzerBlock;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

//The role of this class is to destroy the eletrolyser when a mixer or presser be set on it.
//The role to do so is to avoid some strange bugs.
@EventBusSubscriber(modid = CreateElectrolysis.MODID)
public class PlacementGuard {

    @SubscribeEvent
    public static void onEntityPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getLevel() instanceof Level level) || level.isClientSide())
            return;

        BlockPos placedPos = event.getPos();
        BlockEntity placedBE = level.getBlockEntity(placedPos);
        if (!(placedBE instanceof BasinOperatingBlockEntity)) return;

        BlockPos belowPos = placedPos.below();
        if (!(level.getBlockState(belowPos).getBlock() instanceof ElectrolyzerBlock))
            return;

        level.destroyBlock(belowPos, true);
    }
}