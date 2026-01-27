package net.sean.emporium.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.custom.OpossumEntity;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> OPOSSUM_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(AnimalEmporium.MOD_ID,"opossum"));

    public static final EntityType<OpossumEntity> OPOSSUM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(AnimalEmporium.MOD_ID, "opossum"),
            EntityType.Builder.create(OpossumEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f,0.5f).build(OPOSSUM_KEY));
}
