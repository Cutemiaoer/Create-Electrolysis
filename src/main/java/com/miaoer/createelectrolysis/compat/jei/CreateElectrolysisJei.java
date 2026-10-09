package com.miaoer.createelectrolysis.compat.jei;

import com.miaoer.createelectrolysis.CreateElectrolysis;
import com.miaoer.createelectrolysis.ModBlocks;
import com.miaoer.createelectrolysis.ModRecipeTypes;
import com.miaoer.createelectrolysis.compat.jei.categroy.ElectrolysisCategory;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;


@JeiPlugin
@ParametersAreNonnullByDefault
public class CreateElectrolysisJei implements IModPlugin {
    public static final ResourceLocation ID = CreateElectrolysis.asResource("jei_plugin");

    public CreateRecipeCategory<BasinRecipe> ELECTROLYSIS;

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() { return ID; }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        this.ELECTROLYSIS = builder(BasinRecipe.class)
                .addTypedRecipes(ModRecipeTypes.ELECTROLYSIS)
                .catalyst(ModBlocks.ELECTROLYZER::get)
                .catalyst(AllBlocks.BASIN::get)
                .doubleItemIcon(ModBlocks.ELECTROLYZER.get(),AllBlocks.BASIN.get())
                .emptyBackground(177,103)
                .build("electrolysis",ElectrolysisCategory::new);

        registration.addRecipeCategories(ELECTROLYSIS);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        this.ELECTROLYSIS.registerRecipes(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) { this.ELECTROLYSIS.registerCatalysts(registration); }

    //The scripts below are coped from com.simibubi.create.compat.jei.CreateJEI
    private <T extends Recipe<? extends RecipeInput>> CreateElectrolysisJei.CategoryBuilder<T> builder(Class<T> recipeClass) { return new CategoryBuilder<>(recipeClass); }

    private static class CategoryBuilder<T extends Recipe<?>> extends CreateRecipeCategory.Builder<T> {
        public CategoryBuilder(Class<? extends T> recipeClass) {
            super(recipeClass);
        }

        @Override
        @Nonnull
        public CreateRecipeCategory<T> build(ResourceLocation id, CreateRecipeCategory.@NotNull Factory<T> factory) {
            return super.build(id, factory);
        }
    }
}
