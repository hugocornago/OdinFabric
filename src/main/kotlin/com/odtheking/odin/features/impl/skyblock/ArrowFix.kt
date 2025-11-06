package com.odtheking.odin.features.impl.skyblock

import com.odtheking.odin.events.ItemUseEvent
import com.odtheking.odin.features.Module
import com.odtheking.odin.utils.loreString
import meteordevelopment.orbit.EventHandler
import net.minecraft.item.BowItem

object ArrowFix : Module(
    name = "ArrowFix",
    description = "Fix pullback on shortbows:"
) {
    private const val shortBowString = "Shortbow: Instantly shoots!";
    @EventHandler
    fun onItemUse(event: ItemUseEvent) = with(event.item) {
        if (this.item !is BowItem) return;
        if (this.loreString.contains(shortBowString)) {
            event.cancel()
        }
    }
}