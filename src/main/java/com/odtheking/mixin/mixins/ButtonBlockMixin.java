package com.odtheking.mixin.mixins;

import com.odtheking.odin.features.impl.dungeon.SecretHitboxes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Function;

@Mixin(ButtonBlock.class)
public abstract class ButtonBlockMixin extends FaceAttachedHorizontalDirectionalBlock {
    protected ButtonBlockMixin(Properties properties) {
        super(properties);
    }

    @Unique
    private Function<BlockState, VoxelShape> fullShapeFunction() {
        Map<AttachFace, Map<Direction, VoxelShape>> map = Shapes.rotateAttachFace(Block.boxZ(16.0, 16.0, 12.0, 16.0));
        return this.getShapeForEachState(
                blockState -> (map.get(blockState.getValue(FACE))).get(blockState.getValue(FACING))
        );
    }

    @Inject(method = "getShape", at = @At("RETURN"), cancellable = true)
    void getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext, CallbackInfoReturnable<VoxelShape> cir) {
        if (SecretHitboxes.INSTANCE.getEnabled() && SecretHitboxes.INSTANCE.getButton())
            cir.setReturnValue(this.fullShapeFunction().apply(blockState));
    }
}
