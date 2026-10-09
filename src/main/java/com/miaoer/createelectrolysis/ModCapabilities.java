package com.miaoer.createelectrolysis;

import com.miaoer.createelectrolysis.electrolyzer.ElectrolyzerBlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.ELECTROLYZER.get(),
                (blockEntity, side) -> {
                    if (blockEntity instanceof ElectrolyzerBlockEntity) {
                        return blockEntity.getEnergyStorage();
                    }
                    return null;
                }
        );
    }
}
