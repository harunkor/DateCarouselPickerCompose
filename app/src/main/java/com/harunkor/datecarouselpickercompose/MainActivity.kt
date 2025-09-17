package com.harunkor.datecarouselpickercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.harunkor.datecarousel.component.DateCarouselPicker
import com.harunkor.datecarousel.data.nextDaysMockHasDot
import com.harunkor.datecarousel.domain.findIndexByMonthOffsetAndDay
import com.harunkor.datecarousel.domain.findItemByMonthOffsetAndDay
import com.harunkor.datecarouselpickercompose.ui.theme.DateCarouselPickerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DateCarouselPickerComposeTheme {

                val context = LocalContext.current
                val items = rememberSaveable { nextDaysMockHasDot(45) }
                //val items = rememberSaveable { nextDays(45) }


                val startIndex =
                    findIndexByMonthOffsetAndDay(items, monthOffset = 0, dayOfMonth = 25) ?: 0
                val nextItem10DayOfMonth = findItemByMonthOffsetAndDay(items, 1, 10)

                val nextItem31DayOfMonth = findItemByMonthOffsetAndDay(items, 1, 31)

                var selectedIndex by rememberSaveable { mutableStateOf(startIndex) }
                var selectedItem by rememberSaveable { mutableStateOf(nextItem10DayOfMonth) }
                var selectedItemAmber by rememberSaveable { mutableStateOf(nextItem31DayOfMonth) }


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Spacer(Modifier.height(16.dp))
                        DateCarouselPicker(
                            items = items,
                            selectedIndex = selectedIndex,
                            onSelect = { item, index ->
                                selectedIndex = index
                                Toast.makeText(
                                    context, "Selected ${item.date}", Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(16.dp))

                        // Red select color
                        DateCarouselPicker(
                            items = items,
                            selectedItem = selectedItem,
                            onSelect = { item, index ->
                                selectedItem = item
                                Toast.makeText(
                                    context, "Selected ${item.date}", Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            selectedColor = Color.Red,
                            shape = AbsoluteCutCornerShape(16.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        // Amber select color
                        DateCarouselPicker(
                            items = items,
                            selectedItem = selectedItemAmber,
                            onSelect = { item, index ->
                                selectedItemAmber = item
                                Toast.makeText(
                                    context, "Selected ${item.date}", Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            selectedColor = Color(0xFFFFC107),
                            shape = AbsoluteRoundedCornerShape(16.dp)
                        )

                    }

                }
            }
        }
    }
}