package com.swordfish.touchinput.radial.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.swordfish.touchinput.radial.LocalLemuroidPadTheme
import com.swordfish.touchinput.radial.controls.LemuroidControlCross
import com.swordfish.touchinput.radial.controls.LemuroidControlFaceButtons
import com.swordfish.touchinput.radial.settings.TouchControllerSettingsManager
import com.swordfish.touchinput.radial.ui.LemuroidButtonForeground
import com.swordfish.touchinput.radial.ui.LemuroidControlBackground
import gg.padkit.PadKitScope
import gg.padkit.controls.ControlButton
import gg.padkit.ids.Id
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap

@Composable
fun PadKitScope.OneHandedLayout(
    modifier: Modifier = Modifier,
    settings: TouchControllerSettingsManager.Settings,
    dpadId: Id.DiscreteDirection?,
    faceButtonIds: PersistentList<Id.Key>,
    faceButtonForegrounds: PersistentMap<Id.Key, @Composable (State<Boolean>) -> Unit>,
    lId: Id.Key? = null,
    rId: Id.Key? = null,
    l2Id: Id.Key? = null,
    r2Id: Id.Key? = null,
    startId: Id.Key? = null,
    selectId: Id.Key? = null,
    menuId: Id.Key? = null,
) {
    val isLeftHanded = settings.oneHandedIsLeft

    val interpolatedScale = remember(settings.scale) {
        lerp(
            TouchControllerSettingsManager.MIN_SCALE,
            TouchControllerSettingsManager.MAX_SCALE,
            settings.scale,
        )
    }

    val buttonScale = settings.buttonScale
    val mainControlSize = 160.dp * interpolatedScale * buttonScale
    val secondaryButtonSize = 56.dp * interpolatedScale * buttonScale

    val alignment = if (isLeftHanded) Alignment.BottomStart else Alignment.BottomEnd

    Box(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (isLeftHanded) {
                    Modifier.absolutePadding(
                        left = TouchControllerSettingsManager.MAX_MARGINS.dp * settings.marginX,
                        bottom = TouchControllerSettingsManager.MAX_MARGINS.dp * settings.marginY,
                    )
                } else {
                    Modifier.absolutePadding(
                        right = TouchControllerSettingsManager.MAX_MARGINS.dp * settings.marginX,
                        bottom = TouchControllerSettingsManager.MAX_MARGINS.dp * settings.marginY,
                    )
                }
            )
            .padding(16.dp),
        contentAlignment = alignment
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(16.dp * interpolatedScale)
        ) {
            val mainControlsBlock = @Composable {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp * interpolatedScale)
                ) {
                    // Face Buttons (A, B, X, Y...)
                    LemuroidControlFaceButtons(
                        modifier = Modifier.size(mainControlSize),
                        ids = faceButtonIds,
                        idsForegrounds = faceButtonForegrounds
                    )

                    // D-Pad
                    if (dpadId != null) {
                        LemuroidControlCross(
                            modifier = Modifier.size(mainControlSize),
                            id = dpadId
                        )
                    }
                }
            }

            val inwardControlsBlock = @Composable {
                Column(
                    modifier = Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp * interpolatedScale),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Shoulders L1 / R1
                    if (lId != null || rId != null) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp * interpolatedScale)
                        ) {
                            if (lId != null) {
                                OneHandedButton(id = lId, label = "L", buttonSize = secondaryButtonSize)
                            }
                            if (rId != null) {
                                OneHandedButton(id = rId, label = "R", buttonSize = secondaryButtonSize)
                            }
                        }
                    }

                    // Secondary Shoulders L2 / R2
                    if (l2Id != null || r2Id != null) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp * interpolatedScale)
                        ) {
                            if (l2Id != null) {
                                OneHandedButton(id = l2Id, label = "L2", buttonSize = secondaryButtonSize)
                            }
                            if (r2Id != null) {
                                OneHandedButton(id = r2Id, label = "R2", buttonSize = secondaryButtonSize)
                            }
                        }
                    }

                    // System buttons
                    if (menuId != null) {
                        OneHandedButton(
                            id = menuId,
                            icon = com.swordfish.touchinput.controller.R.drawable.button_menu,
                            buttonSize = secondaryButtonSize
                        )
                    }
                    if (selectId != null) {
                        OneHandedButton(
                            id = selectId,
                            icon = com.swordfish.touchinput.controller.R.drawable.button_select,
                            buttonSize = secondaryButtonSize
                        )
                    }
                    if (startId != null) {
                        OneHandedButton(
                            id = startId,
                            icon = com.swordfish.touchinput.controller.R.drawable.button_start,
                            buttonSize = secondaryButtonSize
                        )
                    }
                }
            }

            if (isLeftHanded) {
                // Left side: Main controls on outer left, inward secondary buttons on the right
                mainControlsBlock()
                inwardControlsBlock()
            } else {
                // Right side: Inward secondary buttons on the left, main controls on outer right
                inwardControlsBlock()
                mainControlsBlock()
            }
        }
    }
}

@Composable
private fun PadKitScope.OneHandedButton(
    id: Id.Key,
    label: String? = null,
    icon: Int? = null,
    buttonSize: Dp = 56.dp,
) {
    val theme = LocalLemuroidPadTheme.current
    ControlButton(
        modifier = Modifier
            .size(buttonSize)
            .padding(theme.padding),
        id = id,
        foreground = { LemuroidButtonForeground(pressed = it, icon = icon, label = label) },
        background = { LemuroidControlBackground() },
    )
}
