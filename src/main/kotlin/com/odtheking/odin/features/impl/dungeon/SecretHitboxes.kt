package com.odtheking.odin.features.impl.dungeon

import com.odtheking.odin.clickgui.settings.impl.BooleanSetting
import com.odtheking.odin.features.Module

object SecretHitboxes : Module(
    name = "Secret Hitboxes",
    description = "Changes the hitboxes of secrets."
) {
    val lever by BooleanSetting("Lever", false, desc = "Extends the lever hitbox.")
    val button by BooleanSetting("Button", false, desc = "Extends the button hitbox.")
}