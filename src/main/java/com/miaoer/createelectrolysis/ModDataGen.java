package com.miaoer.createelectrolysis;

import com.miaoer.createelectrolysis.datagen.EnUsLangGen;
import com.miaoer.createelectrolysis.datagen.ZhCnLangGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CreateElectrolysis.MODID)
public class ModDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        gen.addProvider(event.includeClient(), new EnUsLangGen(output));//Chinese
        gen.addProvider(event.includeClient(), new ZhCnLangGen(output));//English
    }
}
