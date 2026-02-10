package com.example.lablearnandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RPGCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RPGCardView()
        }
    }

    @Composable
    fun RPGCardView() {
        // กำหนดธีมสี
        val darkBackground = Color(0xFF1A1A1A)
        val cardBackground = Color(0xFF2D2D2D)
        val goldAccent = Color(0xFFFFD700)
        val hpColor = Color(0xFFE53935)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(darkBackground, Color.Black)))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(1.dp, goldAccent.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // --- HP Bar Section ---
                    Text(
                        text = "HEALTH BAR",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.72f) // HP ปัจจุบัน
                                .fillMaxHeight()
                                .background(Brush.horizontalGradient(listOf(hpColor, Color(0xFFB71C1C))))
                        )
                    }

                    // --- Character Image ---
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .border(4.dp, goldAccent, CircleShape)
                            .clickable {
                                startActivity(Intent(this@RPGCardActivity, LifeCycleComposeActivity::class.java))
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_profile), // ตรวจสอบว่ามีไฟล์นี้ใน res/drawable
                            contentDescription = "Character Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = "Chinchilla",
                        color = goldAccent,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    // --- Stats Section ---
                    Divider(color = Color.Gray.copy(alpha = 0.3f), modifier = Modifier.padding(bottom = 16.dp))

                    var str by remember { mutableStateOf(8) }
                    var agi by remember { mutableStateOf(10) }
                    var int by remember { mutableStateOf(15) }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem("STR", str, onIncrease = { str++ }, onDecrease = { if (str > 0) str-- })
                        StatItem("AGI", agi, onIncrease = { agi++ }, onDecrease = { if (agi > 0) agi-- })
                        StatItem("INT", int, onIncrease = { int++ }, onDecrease = { if (int > 0) int-- })
                    }
                }
            }
        }
    }

    @Composable
    fun StatItem(label: String, value: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, color = Color.LightGray, fontSize = 14.sp, fontWeight = FontWeight.Bold)

            FilledIconButton(
                onClick = onIncrease,
                modifier = Modifier.size(32.dp).padding(vertical = 4.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF444444))
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(16.dp))
            }

            Text(
                text = value.toString(),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            FilledIconButton(
                onClick = onDecrease,
                modifier = Modifier.size(32.dp).padding(vertical = 4.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF444444))
            ) {
                Icon(Icons.Default.Remove, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
}