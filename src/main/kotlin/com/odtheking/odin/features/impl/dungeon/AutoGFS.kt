package com.odtheking.odin.features.impl.dungeon

import com.odtheking.odin.clickgui.settings.impl.BooleanSetting
import com.odtheking.odin.clickgui.settings.impl.NumberSetting
import com.odtheking.odin.features.Module
import com.odtheking.odin.utils.customData
import com.odtheking.odin.utils.fillItemFromSack
import com.odtheking.odin.utils.handlers.TickTask
import com.odtheking.odin.utils.itemId
import com.odtheking.odin.utils.skyblock.dungeon.DungeonUtils

object AutoGFS : Module(
    name = "Auto GFS",
    description = "Automatically refills certain items from your sacks."
) {
    private val refillOnDungeonStart by BooleanSetting("Refill on Dungeon Start", true, desc = "Refill when a dungeon starts.")
    private val refillPearl by BooleanSetting("Refill Pearl", true, desc = "Refill ender pearls.")
    private val refillJerry by BooleanSetting("Refill Jerry", true, desc = "Refill inflatable jerrys.")
    private val refillTNT by BooleanSetting("Refill TNT", true, desc = "Refill superboom tnt.")
    private val refillLeap by BooleanSetting("Refill Leap", true, desc = "Refill spirit leap.")
    private val refillOnTimer by BooleanSetting("Refill on Timer", true, desc = "Refill on a 5s intervals.")
    private val timerIncrements by NumberSetting("Timer Increments", 5L, 1, 60, desc = "The interval in which to refill.", unit = "s")
    private val autoGetDraft by BooleanSetting("Auto Get Draft", true, desc = "Automatically get draft from the sack.")

    init {
        TickTask((timerIncrements * 20).toInt()) {
            if (refillOnTimer) refill()
        }

//        onMessage(Regex("^PUZZLE FAIL! (\\w{1,16}) .+$|^\\[STATUE\\] Oruo the Omniscient: (\\w{1,16}) chose the wrong answer! I shall never forget this moment of misrememberance\\.$")) {
//            if (!autoGetDraft || DungeonUtils.currentRoom?.data?.type != RoomType.PUZZLE) return@onMessage
//            runIn(30) {
//                modMessage("§7Fetching Draft from sack...")
//                sendCommand("gfs architect's first draft 1")
//            }
//        }
    }

    private fun refill() {
        if (mc.screen != null || !DungeonUtils.inDungeons) return
        val inventory = mc.player?.inventory ?: return

        inventory.find { it?.customData?.itemId == "ENDER_PEARL" }?.takeIf { refillPearl }?.also { fillItemFromSack(16, "ENDER_PEARL", "ender_pearl", false) }

        inventory.find { it?.customData?.itemId == "INFLATABLE_JERRY" }?.takeIf { refillJerry }?.also { fillItemFromSack(64, "INFLATABLE_JERRY", "inflatable_jerry", false) }

        // TODO: untested
        inventory.find { it?.customData?.itemId == "SPIRIT_LEAP" }?.takeIf { refillLeap }?.also { fillItemFromSack(64, "SPIRIT_LEAP", "spirit_leap", false) }

        inventory.find { it?.customData?.itemId == "SUPERBOOM_TNT" }.takeIf { refillTNT }?.also { fillItemFromSack(64, "SUPERBOOM_TNT", "superboom_tnt", false) }
    }
}
