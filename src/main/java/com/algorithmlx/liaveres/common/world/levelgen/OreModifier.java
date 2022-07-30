package com.algorithmlx.liaveres.common.world.levelgen;

import com.algorithmlx.liaveres.common.setup.Registration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class OreModifier implements BiomeModifier {
    public static final Codec<OreModifier> CODEC = RecordCodecBuilder.<OreModifier>mapCodec(cod3c -> cod3c.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(ore -> ore.biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(ore -> ore.feature)
            ).apply(cod3c, OreModifier::new)).codec();

    private final HolderSet<Biome> biomes;
    private final Holder<PlacedFeature> feature;

    protected OreModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) {
        this.biomes = biomes;
        this.feature = feature;
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, this.feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return Registration.ORE_CODEC.get();
    }
}
