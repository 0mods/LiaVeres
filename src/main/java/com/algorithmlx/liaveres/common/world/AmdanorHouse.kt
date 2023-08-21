package com.algorithmlx.liaveres.common.world

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.feature.StructureFeature
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import java.util.Optional

class AmdanorHouse: StructureFeature<JigsawConfiguration>(codec, AmdanorHouse::createPieces, PostPlacementProcessor.NONE) {
    override fun step(): GenerationStep.Decoration = GenerationStep.Decoration.SURFACE_STRUCTURES

    companion object {
        val codec = RecordCodecBuilder.create { codec ->
            codec.group(
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                Codec.intRange(0, 30).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
            ).apply(codec, ::JigsawConfiguration)
        }

        private fun isFeatureChunk(context: PieceGeneratorSupplier.Context<JigsawConfiguration>): Boolean {
            val chunkPos = context.chunkPos
            return !context.chunkGenerator.hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, context.seed, chunkPos.x, chunkPos.z, 10)
        }

        fun createPieces(context: PieceGeneratorSupplier.Context<JigsawConfiguration>): Optional<PieceGenerator<JigsawConfiguration>> {
            if (!isFeatureChunk(context)) {
                return Optional.empty()
            }

            var pos = context.chunkPos.getMiddleBlockPosition(0)
            pos = pos.above(60)

            return JigsawPlacement.addPieces(context, ::PoolElementStructurePiece, pos, false, true)
        }
    }
}
