package com.miaoer.createelectrolysis.electrolyzer;

import com.miaoer.createelectrolysis.ModRecipeTypes;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

import java.util.List;
import java.util.Optional;

public class ElectrolyzerBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {
    private int progress;
    private Recipe<?> currentRecipe;//The recipe be matched
    private final EnergyStorage energyStorage = new EnergyStorage(5000);

    public ElectrolyzerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) return;
        //Edit the need of energy per time here.
        int energyNeeded = 240;

        if(energyStorage.getEnergyStored() < energyNeeded){
            resetProgress();
            return;
        }

        Optional<BasinBlockEntity> basinOpt = findBasinBelow();
        if (basinOpt.isEmpty()) {
            resetProgress();
            return;
        }
        BasinBlockEntity basin = basinOpt.get();

        if (currentRecipe == null) {
            //match recipe form all recipes
            for (RecipeHolder<?> holder : level.getRecipeManager()
                    .getAllRecipesFor(ModRecipeTypes.ELECTROLYSIS.getType())) {
                Recipe<?> r = holder.value();
                if (BasinRecipe.match(basin, r)) {
                    currentRecipe = r;
                    break;
                }
            }
            if (currentRecipe == null) {
                resetProgress();
                return;
            }
        }

        if (!BasinRecipe.match(basin, currentRecipe)) {
            resetProgress();
            return;
        }

        progress++;
        if (progress >= ((StandardProcessingRecipe<?>) currentRecipe).getProcessingDuration()) {
            energyStorage.extractEnergy(energyNeeded,false);
            BasinRecipe.apply(basin, currentRecipe);
            resetProgress();
            level.playSound(null, worldPosition, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT,
                    SoundSource.BLOCKS, .75f, .75f);
            sendData();
        }
    }

    private Optional<BasinBlockEntity> findBasinBelow() {
        if (level == null) return Optional.empty();
        var be = level.getBlockEntity(worldPosition.below());
        return (be instanceof BasinBlockEntity b) ? Optional.of(b) : Optional.empty();
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(tag, registries, clientPacket);
        tag.putInt("Progress", progress);
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(tag, registries, clientPacket);
        progress = tag.getInt("Progress");
        currentRecipe = null;
    }

    private void resetProgress() {
        if (progress != 0 || currentRecipe != null) {
            progress = 0;
            currentRecipe = null;
            setChanged();
        }
    }

    public EnergyStorage getEnergyStorage() {
        return energyStorage;
    }
}
