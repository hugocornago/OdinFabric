//package com.odtheking.mixin.mixins;
//
//import com.odtheking.odin.features.impl.dungeon.SecretHitboxes;
//import net.minecraft.block.*;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.shape.VoxelShape;
//import net.minecraft.util.shape.VoxelShapes;
//import net.minecraft.world.BlockView;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.*;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(LeverBlock.class)
//public abstract class LeverBlockMixin extends WallMountedBlock {
//    protected LeverBlockMixin(Settings settings) {
//        super(settings);
//    }
//
//    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
//    private void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
//        if (SecretHitboxes.INSTANCE.getEnabled() && SecretHitboxes.INSTANCE.getLever())
//            cir.setReturnValue(VoxelShapes.fullCube());
//    }
//}
