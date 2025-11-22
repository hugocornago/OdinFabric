package com.odtheking.odin.features.impl.dungeon

import com.odtheking.odin.events.GuiEvent
import com.odtheking.odin.events.core.on
import com.odtheking.odin.features.Module
import com.odtheking.odin.utils.equalsOneOf
import com.odtheking.odin.utils.skyblock.dungeon.DungeonUtils
import net.minecraft.client.gui.screens.inventory.ContainerScreen
import net.minecraft.network.chat.contents.TranslatableContents

object CloseChest : Module(
    name = "Close Chest",
    description = "Allows you to instantly close chests with any key or automatically."
) {
    private const val CHESTKEY = "container.chest";
    private const val DOUBLECHESTKEY = "container.chestDouble";

    fun shouldCloseScreen(screen: ContainerScreen): Boolean? {
        val key = (screen.title.contents as? TranslatableContents)?.key ?: return false
        return key.equalsOneOf(CHESTKEY, DOUBLECHESTKEY)
    }

    init {
        on<GuiEvent.KeyPress> {
            if (!DungeonUtils.inDungeons) return@on
            val screen = this.screen as? ContainerScreen ?: return@on
            if (shouldCloseScreen(screen) == true) {
                this.cancel()
                this.screen.onClose()
            }
        }

        on<GuiEvent.MouseClick> {
            if (!DungeonUtils.inDungeons) return@on
            val screen = this.screen as? ContainerScreen ?: return@on
            if (shouldCloseScreen(screen) == true) {
                this.cancel()
                this.screen.onClose()
            }
        }
    }
}