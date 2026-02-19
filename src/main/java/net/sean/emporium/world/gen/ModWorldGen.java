package net.sean.emporium.world.gen;

import net.sean.emporium.AnimalEmporium;

public class ModWorldGen {
    public static void generateWorldGen() {
        AnimalEmporium.LOGGER.info("Registering ModWorldGen for " + AnimalEmporium.MOD_ID);
        ModEntitySpawn.addEntitySpawn();
    }
}
