package com.miaoer.createelectrolysis.datagen;

import com.miaoer.createelectrolysis.CreateElectrolysis;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ZhCnLangGen extends LanguageProvider {
    public ZhCnLangGen(PackOutput gen) {
        super(gen, CreateElectrolysis.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.createelectrolysis", "机械动力：电解");
        add("create.recipe.electrolysis", "电解");

        add("item.createelectrolysis.rods", "电极");
        add("block.createelectrolysis.electrolyzer", "电解机");
        add("fluid.createelectrolysis.hydrogen", "氢气");
        add("fluid.createelectrolysis.oxygen", "氧气");

        add("block.createelectrolysis.electrolyzer.tooltip.summary", "当电解机中有足够的_FE能量_时，电解机会电解下方_工作盆_中的流体。");
    }
}
