package com.odtheking.odin.features.impl.skyblock

import com.odtheking.odin.events.ItemUseEvent
import com.odtheking.odin.events.core.on
import com.odtheking.odin.features.Module
import com.odtheking.odin.utils.loreString

object ArrowFix : Module(
    name = "ArrowFix",
    description = "Fix pullback on shortbows:"
) {
    private const val shortBowString = "Shortbow: Instantly shoots!";

    init {
        on<ItemUseEvent> {
            if (this.item.loreString.contains(shortBowString)) {
                this.cancel();
            }
        }
    }
}