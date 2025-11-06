package com.odtheking.mixin.mixins;

import com.odtheking.odin.events.ItemUseEvent;
import com.odtheking.odin.utils.skyblock.LocationUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    void fixPullBack(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!LocationUtils.INSTANCE.isInSkyblock()) return;

        ItemStack item = user.getStackInHand(hand);
        if (new ItemUseEvent(item).postAndCatch()) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        };
    }
}
