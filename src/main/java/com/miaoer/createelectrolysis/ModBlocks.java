package com.miaoer.createelectrolysis;

import com.miaoer.createelectrolysis.electrolyzer.ElectrolyzerBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.item.ItemDescription;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getHasName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;


public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = CreateElectrolysis.registrate();
    static {
        REGISTRATE.setCreativeTab(AllCreativeModeTabs.BASE_CREATIVE_TAB);
    }
    public static final BlockEntry<ElectrolyzerBlock> ELECTROLYZER = REGISTRATE
            .block("electrolyzer", ElectrolyzerBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.STONE))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .item()
            .transform(customItemModel())
            .onRegisterAfter(Registries.ITEM,v -> ItemDescription.useKey(v, "block.createelectrolysis.electrolyzer"))
            .recipe((ctx,provider)-> ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ctx.get())
                    .pattern("A")
                    .pattern("B")
                    .pattern("C")
                    .define('A', AllItems.ELECTRON_TUBE)
                    .define('B', AllBlocks.ANDESITE_CASING)
                    .define('C', ModItems.RODS)
                    .unlockedBy(getHasName(AllBlocks.ANDESITE_CASING),has(AllBlocks.ANDESITE_CASING))
                    .save(provider,CreateElectrolysis.MODID+":shaped/electrolyzer"))
            .register();

    public static void register(){}
}
