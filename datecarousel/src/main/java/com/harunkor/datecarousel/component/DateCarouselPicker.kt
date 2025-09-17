package com.harunkor.datecarousel.component

/**
 * --------------------------------------------
 * Developer: Harun Kör
 * Title    : Senior Android Developer
 * Website  : www.harunkor.com.tr
 * GitHub   : https://github.com/harunkor
 * --------------------------------------------
 *
 */
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harunkor.datecarousel.model.DayItem
import kotlinx.coroutines.launch

@Composable
fun DateCarouselPicker(
    items: List<DayItem>,
    selectedIndex: Int? = null,
    selectedItem: DayItem? = null,
    onSelect: (item: DayItem, index: Int) -> Unit,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 76.dp,
    itemHeight: Dp = 96.dp,
    spacing: Dp = 12.dp,
    selectedColor: Color = Color(0xFFFF5A1F),
    unselectedFill: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
    unselectedContent: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shape: Shape = RoundedCornerShape(42.dp),
    dayTextStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(
        fontSize = 24.sp, fontWeight = FontWeight.ExtraBold
    ),
    weekdayTextStyle: TextStyle = MaterialTheme.typography.labelMedium.copy(
        fontSize = 12.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.1.sp
    )
) {
    val extSelectedIndex = when {
        selectedItem != null -> items.indexOf(selectedItem)
        selectedIndex != null -> selectedIndex
        else -> -1
    }.coerceAtLeast(0)
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = extSelectedIndex
    )
    val scope = rememberCoroutineScope()
    val haptics = LocalHapticFeedback.current


    LazyRow(
        modifier = modifier,
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(items) { index, item ->
            val isSelected = when {
                selectedItem != null -> item == selectedItem
                selectedIndex != null -> index == selectedIndex
                else -> false
            }

            val bg by animateColorAsState(
                targetValue = if (isSelected) selectedColor else unselectedFill,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "bg"
            )
            val contentColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else unselectedContent,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "fg"
            )
            val elevation by animateDpAsState(
                targetValue = if (isSelected) 6.dp else 0.dp,
                animationSpec = spring(stiffness = Spring.StiffnessLow),
                label = "elev"
            )
            val scalePadding by animateDpAsState(
                targetValue = if (isSelected) 0.dp else 2.dp,
                animationSpec = spring(stiffness = Spring.StiffnessLow),
                label = "pad"
            )

            Surface(
                color = bg,
                shadowElevation = elevation,
                shape = shape,
                modifier = Modifier
                    .size(width = itemWidth, height = itemHeight)
                    .clip(shape)
                    .semantics {
                        contentDescription = "${item.dayOfMonth} ${item.weekdayLabel}"
                    }
                    .clickable {
                        if (!isSelected) {
                            onSelect(item, index)
                            haptics.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                            scope.launch {
                                // Center the selected item when clicked
                                val targetIndex = (index - 1).coerceAtLeast(0)
                                listState.animateScrollToItem(targetIndex)
                            }
                        }
                    }
                    .padding(scalePadding)) {
                Box(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Month label (e.g., OCT, EKI)
                        ProvideTextStyle(
                            MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = contentColor.copy(alpha = 0.9f)
                            )
                        ) {
                            Text(text = item.monthLabel)
                        }

                        Spacer(Modifier.height(2.dp))

                        // Big day number
                        Box(contentAlignment = Alignment.Center) {
                            ProvideTextStyle(
                                dayTextStyle.copy(
                                    color = contentColor
                                )
                            ) {
                                Text(text = item.dayOfMonth)
                            }

                            if (item.hasDot) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .offset(x = 6.dp, y = 6.dp)
                                        .size(4.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) Color.White else selectedColor)
                                )
                            }
                        }
                        Spacer(Modifier.height(2.dp))
                        // Weekday label
                        ProvideTextStyle(
                            weekdayTextStyle.copy(
                                color = contentColor.copy(alpha = 0.95f)
                            )
                        ) {
                            Text(text = item.weekdayLabel)
                        }
                    }
                }
            }
        }
    }
}