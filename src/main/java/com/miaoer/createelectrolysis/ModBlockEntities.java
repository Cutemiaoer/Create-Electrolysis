package com.miaoer.createelectrolysis;

import com.miaoer.createelectrolysis.electrolyzer.ElectrolyzerBlockEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ModBlockEntities {
    private static final CreateRegistrate REGISTRATE = CreateElectrolysis.registrate();
    public static final BlockEntityEntry<ElectrolyzerBlockEntity> ELECTROLYZER = REGISTRATE
            .blockEntity("electrolyzer", ElectrolyzerBlockEntity::new)
            .validBlock(ModBlocks.ELECTROLYZER)

            .register();

    public static void register(){}
}
