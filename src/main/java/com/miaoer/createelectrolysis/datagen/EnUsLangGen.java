package com.miaoer.createelectrolysis.datagen;

import com.miaoer.createelectrolysis.CreateElectrolysis;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EnUsLangGen extends LanguageProvider {
    public EnUsLangGen(PackOutput gen) {
        super(gen, CreateElectrolysis.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.createelectrolysis", "Create: Electrolysis");
        add("create.recipe.electrolysis", "Electrolysis");

        add("item.createelectrolysis.rods", "Rods");
        add("block.createelectrolysis.electrolyzer", "Electrolyzer");
        add("fluid.createelectrolysis.hydrogen", "Hydrogen");
        add("fluid.createelectrolysis.oxygen", "Oxygen");

        add("block.createelectrolysis.electrolyzer.tooltip.summary", "When there is enough _FE energy_, electrolyse the fluid in the _basin_ below.");
    }
}
