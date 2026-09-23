package com.swordfish.touchinput.radial.settings

import android.content.SharedPreferences
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.max
import androidx.core.content.edit
import com.swordfish.lemuroid.common.compose.pxToDp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import timber.log.Timber

class TouchControllerSettingsManager(private val sharedPreferences: SharedPreferences) {
    enum class Orientation {
        PORTRAIT,
        LANDSCAPE,
    }

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        coerceInputValues = true
    }

    @Serializable
    data class Settings(
        val scale: Float = DEFAULT_SCALE,
        val rotation: Float = DEFAULT_ROTATION,
        val marginX: Float = DEFAULT_MARGIN_X,
        val marginY: Float = DEFAULT_MARGIN_Y,
        val oneHandedMode: Boolean = false,
        val oneHandedIsLeft: Boolean = false,
        val buttonScale: Float = DEFAULT_BUTTON_SCALE,
        val screenOffsetY: Float = DEFAULT_SCREEN_OFFSET_Y,
        val screenSizeScale: Float = DEFAULT_SCREEN_SIZE_SCALE,
    )

    private fun computeInsetsPaddings(
        density: Density,
        insets: WindowInsets,
    ): PaddingValues {
        val result =
            PaddingValues(
                insets.getLeft(density, layoutDirection = LayoutDirection.Ltr).pxToDp(density),
                insets.getTop(density).pxToDp(density),
                insets.getRight(density, layoutDirection = LayoutDirection.Ltr).pxToDp(density),
                insets.getBottom(density).pxToDp(density),
            )
        return result
    }

    private val cachedSettings = mutableMapOf<String, MutableStateFlow<Settings?>>()

    fun observeSettings(
        touchControllerID: TouchControllerID,
        orientation: Orientation,
        density: Density,
        insets: WindowInsets,
    ): Flow<Settings> {
        val paddings = computeInsetsPaddings(density, insets)
        val horizontalPadding =
            max(
                paddings.calculateLeftPadding(LayoutDirection.Ltr),
                paddings.calculateRightPadding(LayoutDirection.Ltr),
            )
        val verticalPadding = paddings.calculateBottomPadding()
        val defaultSettings =
            Settings(
                scale = DEFAULT_SCALE,
                rotation = DEFAULT_ROTATION,
                marginX = horizontalPadding.value / MAX_MARGINS,
                marginY = verticalPadding.value / MAX_MARGINS,
                oneHandedMode = false,
                oneHandedIsLeft = false,
                buttonScale = DEFAULT_BUTTON_SCALE,
                screenOffsetY = DEFAULT_SCREEN_OFFSET_Y,
                screenSizeScale = DEFAULT_SCREEN_SIZE_SCALE,
            )
        val settingsKey = getPreferenceString(touchControllerID, orientation)
        val cachedStateFlow =
            cachedSettings.getOrPut(settingsKey) {
                val currentSettings =
                    sharedPreferences.getString(settingsKey, null)
                        ?.let { json.decodeFromString(Settings.serializer(), it) }

                MutableStateFlow(currentSettings)
            }
        return cachedStateFlow.map { it ?: defaultSettings }
    }

    suspend fun storeSettings(
        touchControllerID: TouchControllerID,
        orientation: Orientation,
        settings: Settings,
    ) {
        Timber.d("Updating touch settings for $touchControllerID at $orientation to $settings")
        updateCachedSettings(touchControllerID, orientation, settings)
        withContext(Dispatchers.IO) {
            sharedPreferences.edit {
                putString(
                    getPreferenceString(touchControllerID, orientation),
                    json.encodeToString(Settings.serializer(), settings),
                )
            }
        }
    }

    private fun updateCachedSettings(
        touchControllerID: TouchControllerID,
        orientation: Orientation,
        settings: Settings?,
    ) {
        val cacheKey = getPreferenceString(touchControllerID, orientation)
        val cacheFlow = cachedSettings.getOrPut(cacheKey) { MutableStateFlow(settings) }
        cacheFlow.value = settings
    }

    suspend fun resetSettings(
        touchControllerID: TouchControllerID,
        orientation: Orientation,
    ) {
        updateCachedSettings(touchControllerID, orientation, null)
        withContext(Dispatchers.IO) {
            sharedPreferences.edit {
                remove(getPreferenceString(touchControllerID, orientation))
            }
        }
    }

    companion object {
        const val DEFAULT_SCALE = 0.5f
        const val DEFAULT_ROTATION = 0.0f
        const val DEFAULT_MARGIN_X = 0.0f
        const val DEFAULT_MARGIN_Y = 0.0f
        const val DEFAULT_BUTTON_SCALE = 1.0f
        const val DEFAULT_SCREEN_OFFSET_Y = 0.0f
        const val DEFAULT_SCREEN_SIZE_SCALE = 1.0f

        const val MIN_BUTTON_SCALE = 0.5f
        const val MAX_BUTTON_SCALE = 1.5f

        const val MIN_SCREEN_OFFSET_Y = -1.0f
        const val MAX_SCREEN_OFFSET_Y = 1.0f

        const val MIN_SCREEN_SIZE_SCALE = 0.3f
        const val MAX_SCREEN_SIZE_SCALE = 1.0f

        const val MAX_ROTATION = 45f
        const val MIN_SCALE = 0.75f
        const val MAX_SCALE = 1.5f

        const val MAX_MARGINS = 96f
    }

    private fun getPreferenceString(
        controllerID: TouchControllerID,
        orientation: Orientation,
    ): String {
        return "touch_controller_settings_${controllerID}_${orientation.ordinal}"
    }
}
