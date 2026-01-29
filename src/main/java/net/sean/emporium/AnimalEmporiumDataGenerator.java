package net.sean.emporium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.util.Identifier;
import net.sean.emporium.datagen.*;

import java.util.Optional;

public class AnimalEmporiumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		// ModModelProvier is crashing datagen... why?!
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
	public static Model item(String parent) {
		return new Model(Optional.of(Identifier.of(AnimalEmporium.MOD_ID, "item/" + parent)), Optional.empty());
	}

}
