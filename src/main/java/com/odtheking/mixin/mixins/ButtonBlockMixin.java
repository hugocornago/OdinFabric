package com.odtheking.mixin.mixins;

import com.odtheking.odin.features.impl.dungeon.SecretHitboxes;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Function;

@Mixin(ButtonBlock.class)
public abstract class ButtonBlockMixin extends WallMountedBlock {

    public ButtonBlockMixin(Settings settings) {
        super(settings);
    }

    private Function<BlockState, VoxelShape> fullShapeFunction() {
        Map<BlockFace, Map<Direction, VoxelShape>> map = VoxelShapes.createBlockFaceHorizontalFacingShapeMap(Block.createCuboidZShape(16.0, 16.0, 12.0, 16.0));
        return this.createShapeFunction(
                state -> (VoxelShape)((Map)map.get(state.get(FACE))).get(state.get(FACING))
        );
    }

    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    private void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (SecretHitboxes.INSTANCE.getEnabled() && SecretHitboxes.INSTANCE.getButton())
            cir.setReturnValue(this.fullShapeFunction().apply(state));
    }
}
