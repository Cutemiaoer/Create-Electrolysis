package com.miaoer.createelectrolysis;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Items;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getHasName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = CreateElectrolysis.registrate();
    static {
        REGISTRATE.setCreativeTab(CreateElectrolysis.CREATIVE_MODE_TAB);
    }

    public static final ItemEntry<Item> RODS  = REGISTRATE.item("rods",Item::new)
            .recipe((ctx,provider)-> ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ctx.get())
                    .pattern("AAA")
                    .pattern("B B")
                    .pattern("B B")
                    .define('A', AllItems.ANDESITE_ALLOY)
                    .define('B', Items.IRON_INGOT)
                    .unlockedBy(getHasName(AllItems.ANDESITE_ALLOY),has(AllItems.ANDESITE_ALLOY))
                    .save(provider,CreateElectrolysis.MODID+":shaped/rods"))
            .register();

    public static void register(){}
}
