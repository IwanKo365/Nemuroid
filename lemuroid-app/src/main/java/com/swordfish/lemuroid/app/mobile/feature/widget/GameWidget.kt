package com.swordfish.lemuroid.app.mobile.feature.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.room.Room
import com.swordfish.lemuroid.app.mobile.feature.main.MainActivity
import com.swordfish.lemuroid.lib.library.GameSystem
import com.swordfish.lemuroid.lib.library.db.RetrogradeDatabase
import com.swordfish.lemuroid.lib.library.db.dao.GameSearchDao
import com.swordfish.lemuroid.lib.library.db.dao.Migrations
import com.swordfish.lemuroid.lib.library.db.entity.Game

class GameWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val db = Room.databaseBuilder(context, RetrogradeDatabase::class.java, RetrogradeDatabase.DB_NAME)
            .addMigrations(GameSearchDao.MIGRATION, Migrations.VERSION_8_9)
            .fallbackToDestructiveMigration()
            .build()
        val lastPlayed = db.gameDao().asyncSelectFirstRecents(1).firstOrNull()
        db.close()

        provideContent {
            CartridgeContent(context, lastPlayed)
        }
    }

    @Composable
    private fun CartridgeContent(context: Context, game: Game?) {
        val systemName = game?.let { context.getString(GameSystem.findById(it.systemId).shortTitleResId) } ?: "Nemuroid"
        val gameTitle = game?.title ?: "No game played yet"

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(0xFF2E2E2E)) // Dark grey cartridge
                .padding(4.dp)
                .clickable(actionStartActivity<MainActivity>()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = GlanceModifier.fillMaxSize().background(Color(0xFF3C3C3C)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // System Label
                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = systemName.uppercase(),
                        style = TextStyle(
                            color = androidx.glance.unit.ColorProvider(Color.White),
                            textAlign = TextAlign.Center
                        )
                    )
                }

                // Divider
                Box(modifier = GlanceModifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.5f))) {}

                // Image / Placeholder
                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Black)
                        .size(height = 60.dp, width = 60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🎮", style = TextStyle(textAlign = TextAlign.Center))
                }

                // Title
                Text(
                    text = gameTitle,
                    modifier = GlanceModifier.padding(start = 4.dp, end = 4.dp, bottom = 8.dp),
                    style = TextStyle(
                        color = androidx.glance.unit.ColorProvider(Color.White),
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 1
                )
            }
        }
    }
}
