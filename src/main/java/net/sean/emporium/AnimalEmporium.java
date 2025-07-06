package net.sean.emporium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sean.emporium.block.ModBlocks;
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
		ModLootTableModifiers.modifyLootTables();
		ModPotions.registerPotions();
		ModPotions.registerPotionsRecipes();
		ModWorldGen.generateWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.OPOSSUM, OpossumEntity.createOpossumAttributes());
	}
}

/* change slop to get from clicking on mud instead of crafting
make opossums spawn in world naturally
 */
