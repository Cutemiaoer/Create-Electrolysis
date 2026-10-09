package com.miaoer.createelectrolysis.electrolyzer;

import com.miaoer.createelectrolysis.ModRecipeTypes;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;

public class ElectorlyzerRecipe extends BasinRecipe {
    public ElectorlyzerRecipe(ProcessingRecipeParams params) {
        super(ModRecipeTypes.ELECTROLYSIS, params);
    }
}
