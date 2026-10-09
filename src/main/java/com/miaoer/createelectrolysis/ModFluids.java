package com.miaoer.createelectrolysis;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;

public class ModFluids {
    private static final CreateRegistrate REGISTRATE = CreateElectrolysis.registrate();

    //There is an important file to find the textures : assets/minecraft/atlases/blocks.json
    static {
        REGISTRATE.setCreativeTab(AllCreativeModeTabs.BASE_CREATIVE_TAB);
    }
    public static final FluidEntry<VirtualFluid> HYDROGEN = REGISTRATE
            .virtualFluid("hydrogen")
            .register();

    public static final FluidEntry<VirtualFluid> OXYGEN = REGISTRATE
            .virtualFluid("oxygen")
            .register();

    public static void register(){}
}
