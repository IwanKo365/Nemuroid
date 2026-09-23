package com.swordfish.touchinput.radial.settings

import android.view.KeyEvent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.swordfish.touchinput.radial.layouts.Arcade4Left
import com.swordfish.touchinput.radial.layouts.Arcade4Right
import com.swordfish.touchinput.radial.layouts.Arcade6Left
import com.swordfish.touchinput.radial.layouts.Arcade6Right
import com.swordfish.touchinput.radial.layouts.Atari2600Left
import com.swordfish.touchinput.radial.layouts.Atari2600Right
import com.swordfish.touchinput.radial.layouts.Atari7800Left
import com.swordfish.touchinput.radial.layouts.Atari7800Right
import com.swordfish.touchinput.radial.layouts.DOSLeft
import com.swordfish.touchinput.radial.layouts.DOSRight
import com.swordfish.touchinput.radial.layouts.DesmumeLeft
import com.swordfish.touchinput.radial.layouts.DesmumeRight
import com.swordfish.touchinput.radial.layouts.GBALeft
import com.swordfish.touchinput.radial.layouts.GBARight
import com.swordfish.touchinput.radial.layouts.GBLeft
import com.swordfish.touchinput.radial.layouts.GBRight
import com.swordfish.touchinput.radial.layouts.GGLeft
import com.swordfish.touchinput.radial.layouts.GGRight
import com.swordfish.touchinput.radial.layouts.Genesis3Left
import com.swordfish.touchinput.radial.layouts.Genesis3Right
import com.swordfish.touchinput.radial.layouts.Genesis6Left
import com.swordfish.touchinput.radial.layouts.Genesis6Right
import com.swordfish.touchinput.radial.layouts.LynxLeft
import com.swordfish.touchinput.radial.layouts.LynxRight
import com.swordfish.touchinput.radial.layouts.MelonDSLeft
import com.swordfish.touchinput.radial.layouts.MelonDSRight
import com.swordfish.touchinput.radial.layouts.N64Left
import com.swordfish.touchinput.radial.layouts.N64Right
import com.swordfish.touchinput.radial.layouts.NESLeft
import com.swordfish.touchinput.radial.layouts.NESRight
import com.swordfish.touchinput.radial.layouts.NGPLeft
import com.swordfish.touchinput.radial.layouts.NGPRight
import com.swordfish.touchinput.radial.layouts.Nintendo3DSLeft
import com.swordfish.touchinput.radial.layouts.Nintendo3DSRight
import com.swordfish.touchinput.radial.layouts.OneHandedLayout
import com.swordfish.touchinput.radial.layouts.PCELeft
import com.swordfish.touchinput.radial.layouts.PCERight
import com.swordfish.touchinput.radial.layouts.PSPLeft
import com.swordfish.touchinput.radial.layouts.PSPRight
import com.swordfish.touchinput.radial.layouts.PSXDualShockLeft
import com.swordfish.touchinput.radial.layouts.PSXDualShockRight
import com.swordfish.touchinput.radial.layouts.PSXLeft
import com.swordfish.touchinput.radial.layouts.PSXRight
import com.swordfish.touchinput.radial.layouts.SMSLeft
import com.swordfish.touchinput.radial.layouts.SMSRight
import com.swordfish.touchinput.radial.layouts.SNESLeft
import com.swordfish.touchinput.radial.layouts.SNESRight
import com.swordfish.touchinput.radial.layouts.WSLandscapeLeft
import com.swordfish.touchinput.radial.layouts.WSLandscapeRight
import com.swordfish.touchinput.radial.layouts.WSPortraitLeft
import com.swordfish.touchinput.radial.layouts.WSPortraitRight
import com.swordfish.touchinput.radial.layouts.shared.ComposeTouchLayouts
import com.swordfish.touchinput.radial.ui.LemuroidButtonForeground
import gg.padkit.PadKitScope
import gg.padkit.ids.Id
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

enum class TouchControllerID {
    GB,
    NES,
    DESMUME,
    MELONDS,
    PSX,
    PSX_DUALSHOCK,
    N64,
    PSP,
    SNES,
    GBA,
    GENESIS_3,
    GENESIS_6,
    ATARI2600,
    SMS,
    GG,
    ARCADE_4,
    ARCADE_6,
    LYNX,
    ATARI7800,
    PCE,
    NGP,
    DOS,
    WS_LANDSCAPE,
    WS_PORTRAIT,
    NINTENDO_3DS,
    ;

    class Config(
        val leftComposable: @Composable PadKitScope.(
            modifier: Modifier,
            settings: TouchControllerSettingsManager.Settings,
        ) -> Unit,
        val rightComposable: @Composable PadKitScope.(
            modifier: Modifier,
            settings: TouchControllerSettingsManager.Settings,
        ) -> Unit,
        val oneHandedComposable: (@Composable PadKitScope.(
            modifier: Modifier,
            settings: TouchControllerSettingsManager.Settings,
        ) -> Unit)? = null,
    )

    companion object {
        fun getConfig(id: TouchControllerID): Config {
            return when (id) {
                GB ->
                    Config(
                        { modifier, settings -> GBLeft(modifier, settings) },
                        { modifier, settings -> GBRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                NES ->
                    Config(
                        { modifier, settings -> NESLeft(modifier, settings) },
                        { modifier, settings -> NESRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                DESMUME ->
                    Config(
                        { modifier, settings -> DesmumeLeft(modifier, settings) },
                        { modifier, settings -> DesmumeRight(modifier, settings) },
                    )

                MELONDS ->
                    Config(
                        { modifier, settings -> MelonDSLeft(modifier, settings) },
                        { modifier, settings -> MelonDSRight(modifier, settings) },
                    )

                PSX ->
                    Config(
                        { modifier, settings -> PSXLeft(modifier, settings) },
                        { modifier, settings -> PSXRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                l2Id = Id.Key(KeyEvent.KEYCODE_BUTTON_L2),
                                r2Id = Id.Key(KeyEvent.KEYCODE_BUTTON_R2),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                PSX_DUALSHOCK ->
                    Config(
                        { modifier, settings -> PSXDualShockLeft(modifier, settings) },
                        { modifier, settings -> PSXDualShockRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                l2Id = Id.Key(KeyEvent.KEYCODE_BUTTON_L2),
                                r2Id = Id.Key(KeyEvent.KEYCODE_BUTTON_R2),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                N64 ->
                    Config(
                        { modifier, settings -> N64Left(modifier, settings) },
                        { modifier, settings -> N64Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                PSP ->
                    Config(
                        { modifier, settings -> PSPLeft(modifier, settings) },
                        { modifier, settings -> PSPRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                SNES ->
                    Config(
                        { modifier, settings -> SNESLeft(modifier, settings) },
                        { modifier, settings -> SNESRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                GBA ->
                    Config(
                        { modifier, settings -> GBALeft(modifier, settings) },
                        { modifier, settings -> GBARight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                GENESIS_3 ->
                    Config(
                        { modifier, settings -> Genesis3Left(modifier, settings) },
                        { modifier, settings -> Genesis3Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_C)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_C) to { LemuroidButtonForeground(pressed = it, label = "C") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                GENESIS_6 ->
                    Config(
                        { modifier, settings -> Genesis6Left(modifier, settings) },
                        { modifier, settings -> Genesis6Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_C),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Z)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_C) to { LemuroidButtonForeground(pressed = it, label = "C") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Z) to { LemuroidButtonForeground(pressed = it, label = "Z") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                ATARI2600 ->
                    Config(
                        { modifier, settings -> Atari2600Left(modifier, settings) },
                        { modifier, settings -> Atari2600Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") }
                                ),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                SMS ->
                    Config(
                        { modifier, settings -> SMSLeft(modifier, settings) },
                        { modifier, settings -> SMSRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_1), Id.Key(KeyEvent.KEYCODE_BUTTON_2)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "2") }
                                ),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                GG ->
                    Config(
                        { modifier, settings -> GGLeft(modifier, settings) },
                        { modifier, settings -> GGRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_1), Id.Key(KeyEvent.KEYCODE_BUTTON_2)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "2") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                ARCADE_4 ->
                    Config(
                        { modifier, settings -> Arcade4Left(modifier, settings) },
                        { modifier, settings -> Arcade4Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_3),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_4)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "2") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_3) to { LemuroidButtonForeground(pressed = it, label = "3") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_4) to { LemuroidButtonForeground(pressed = it, label = "4") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                ARCADE_6 ->
                    Config(
                        { modifier, settings -> Arcade6Left(modifier, settings) },
                        { modifier, settings -> Arcade6Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_3),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_4),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_5),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_6)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "2") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_3) to { LemuroidButtonForeground(pressed = it, label = "3") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_4) to { LemuroidButtonForeground(pressed = it, label = "4") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_5) to { LemuroidButtonForeground(pressed = it, label = "5") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_6) to { LemuroidButtonForeground(pressed = it, label = "6") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                LYNX ->
                    Config(
                        { modifier, settings -> LynxLeft(modifier, settings) },
                        { modifier, settings -> LynxRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                ATARI7800 ->
                    Config(
                        { modifier, settings -> Atari7800Left(modifier, settings) },
                        { modifier, settings -> Atari7800Right(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_1), Id.Key(KeyEvent.KEYCODE_BUTTON_2)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "2") }
                                ),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                PCE ->
                    Config(
                        { modifier, settings -> PCELeft(modifier, settings) },
                        { modifier, settings -> PCERight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_1), Id.Key(KeyEvent.KEYCODE_BUTTON_2)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_1) to { LemuroidButtonForeground(pressed = it, label = "I") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_2) to { LemuroidButtonForeground(pressed = it, label = "II") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                NGP ->
                    Config(
                        { modifier, settings -> NGPLeft(modifier, settings) },
                        { modifier, settings -> NGPRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                DOS ->
                    Config(
                        { modifier, settings -> DOSLeft(modifier, settings) },
                        { modifier, settings -> DOSRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "1") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "2") }
                                ),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                WS_LANDSCAPE ->
                    Config(
                        { modifier, settings -> WSLandscapeLeft(modifier, settings) },
                        { modifier, settings -> WSLandscapeRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                WS_PORTRAIT ->
                    Config(
                        { modifier, settings -> WSPortraitLeft(modifier, settings) },
                        { modifier, settings -> WSPortraitRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(Id.Key(KeyEvent.KEYCODE_BUTTON_A), Id.Key(KeyEvent.KEYCODE_BUTTON_B)),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") }
                                ),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )

                NINTENDO_3DS ->
                    Config(
                        { modifier, settings -> Nintendo3DSLeft(modifier, settings) },
                        { modifier, settings -> Nintendo3DSRight(modifier, settings) },
                        { modifier, settings ->
                            OneHandedLayout(
                                modifier = modifier,
                                settings = settings,
                                dpadId = Id.DiscreteDirection(ComposeTouchLayouts.MOTION_SOURCE_DPAD),
                                faceButtonIds = persistentListOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X),
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y)
                                ),
                                faceButtonForegrounds = persistentMapOf(
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_A) to { LemuroidButtonForeground(pressed = it, label = "A") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_B) to { LemuroidButtonForeground(pressed = it, label = "B") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_X) to { LemuroidButtonForeground(pressed = it, label = "X") },
                                    Id.Key(KeyEvent.KEYCODE_BUTTON_Y) to { LemuroidButtonForeground(pressed = it, label = "Y") }
                                ),
                                lId = Id.Key(KeyEvent.KEYCODE_BUTTON_L1),
                                rId = Id.Key(KeyEvent.KEYCODE_BUTTON_R1),
                                startId = Id.Key(KeyEvent.KEYCODE_BUTTON_START),
                                selectId = Id.Key(KeyEvent.KEYCODE_BUTTON_SELECT),
                                menuId = Id.Key(KeyEvent.KEYCODE_BUTTON_MODE)
                            )
                        }
                    )
            }
        }
    }
}
