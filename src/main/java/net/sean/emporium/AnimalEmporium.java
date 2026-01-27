package net.sean.emporium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.block.entity.ModBlockEntities;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.entity.custom.OpossumEntity;
import net.sean.emporium.fluid.ModFluids;
import net.sean.emporium.item.ModItems;
import net.sean.emporium.potion.ModPotions;
import net.sean.emporium.util.ModLootTableModifiers;
import net.sean.emporium.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimalEmporium implements ModInitializer {

	public static final String MOD_ID = "emporium";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModFluids.registerModFluids();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBE();
		ModLootTableModifiers.modifyLootTables();
		ModPotions.registerPotions();
		ModWorldGen.generateWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.OPOSSUM, OpossumEntity.createOpossumAttributes());
	}

	/*
	TODO
	- if opossum is hurt, grab food from bowl otherwise opossum can just randomly eat without actually taking a worm
	- fix and add more animations
	- somehow fix stupid shadow/hitbox offset
	- implement new inventory texture, figure out if adding colors to bowl is easy with datagen otherwise do that like very very very last thing ever
	- baby opossums able to spawn riding parent? -> when baby grows it should fall off parent automatically
	 */




}


