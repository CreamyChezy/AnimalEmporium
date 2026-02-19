package net.sean.emporium.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.WorldView;
import net.sean.emporium.AnimalEmporium;

public class ModFluids {

    public static FlowableFluid STILL_SLOP = Registry.register(Registries.FLUID, Identifier.of(AnimalEmporium.MOD_ID, "slop"), new SlopFluid.Still() {
        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 4;
        }
    });
    public static FlowableFluid FLOWING_SLOP = Registry.register(Registries.FLUID, Identifier.of(AnimalEmporium.MOD_ID, "flowing_slop"), new SlopFluid.Flowing());
    public static Block SLOP = Registry.register(Registries.BLOCK, Identifier.of(AnimalEmporium.MOD_ID, "slop"), new SlopFluidBlock(STILL_SLOP,
            Block.Settings.copy(Blocks.WATER).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AnimalEmporium.MOD_ID,"slop")))));

        public static void registerModFluids() {
            AnimalEmporium.LOGGER.info("Registering ModFluids for " + AnimalEmporium.MOD_ID);
        }
    }
