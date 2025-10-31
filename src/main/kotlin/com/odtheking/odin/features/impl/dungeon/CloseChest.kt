package com.odtheking.odin.features.impl.dungeon

import com.odtheking.odin.OdinMod
import com.odtheking.odin.events.GuiEvent
import com.odtheking.odin.features.Module
import com.odtheking.odin.utils.equalsOneOf
import com.odtheking.odin.utils.skyblock.dungeon.DungeonUtils
import meteordevelopment.orbit.EventHandler
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen
import net.minecraft.text.TranslatableTextContent

object CloseChest : Module(
    name = "Close Chest",
    description = "Allows you to instantly close chests with any key or automatically."
) {
    private val chestKey = "container.chest";
    private val doubleChestKey = "container.chestDouble";

    fun shouldCloseGUI(gui: GenericContainerScreen?): Boolean? {
        val key = (gui?.title?.content as? TranslatableTextContent)?.key
        return key.equalsOneOf(chestKey, doubleChestKey)
    }

    @EventHandler
    fun onKeyGUI(event: GuiEvent.KeyPress) {
        if (!DungeonUtils.inDungeons) return
        val gui = (event.screen as? GenericContainerScreen)
        if (shouldCloseGUI(gui) == true) {
            event.cancel()
            event.screen.close()
        }
    }

    @EventHandler
    fun onClickGUI(event: GuiEvent.MouseClick) {
        if (!DungeonUtils.inDungeons) return
        val gui = (event.screen as? GenericContainerScreen)
        if (shouldCloseGUI(gui) == true) {
            event.cancel()
            event.screen.close()
        }
    }
}