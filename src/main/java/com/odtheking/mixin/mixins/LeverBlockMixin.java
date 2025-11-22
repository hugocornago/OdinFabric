package com.odtheking.mixin.mixins;

import com.odtheking.odin.features.impl.dungeon.SecretHitboxes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeverBlock.class)
public class LeverBlockMixin {
    @Inject(method = "getShape", at = @At("RETURN"), cancellable = true)
    void getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext, CallbackInfoReturnable<VoxelShape> cir) {
        if (SecretHitboxes.INSTANCE.getEnabled() && SecretHitboxes.INSTANCE.getLever()) {
            if (blockPos.getX() >= 58 && blockPos.getX() <= 62 && blockPos.getY() >= 133 && blockPos.getY() <= 136 && blockPos.getZ() == 142) return;
            cir.setReturnValue(Shapes.block());
        }
    }
}
