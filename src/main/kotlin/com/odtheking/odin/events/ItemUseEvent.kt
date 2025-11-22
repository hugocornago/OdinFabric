package com.odtheking.odin.events

import com.odtheking.odin.events.core.CancellableEvent
import net.minecraft.world.item.ItemStack

class ItemUseEvent(val item: ItemStack) : CancellableEvent()