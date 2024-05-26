package net.sean.emporium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.util.Identifier;
import net.sean.emporium.datagen.*;

import java.util.Optional;

public class AnimalEmporiumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);

	}
	public static Model item(String parent) {
		return new Model(Optional.of(new Identifier(AnimalEmporium.MOD_ID, "item/" + parent)), Optional.empty());
	}

}
