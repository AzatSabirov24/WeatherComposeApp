package com.example.weathercomposeneco.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.ui.theme.BlueLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainCard() {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = 8.dp,
                            start = 8.dp
                        ),
                        text = "21.11.2022 10:54",
                        style = TextStyle(
                            fontSize = 15.sp
                        ),
                        color = Color.White
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(
                                end = 8.dp
                            ),
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "image weather condition"
                    )
                }
                Text(
                    text = "Shymkent",
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    color = Color.White
                )
                Text(
                    text = "23℃",
                    style = TextStyle(
                        fontSize = 72.sp
                    ),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { /* TODO */ }) {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_search_24
                            ),
                            contentDescription = "icon search",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "23℃/13℃",
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        color = Color.White
                    )
                    IconButton(
                        onClick = { /* TODO */ }) {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_sync_24
                            ),
                            contentDescription = "icon search",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val tabList = listOf(
        "HOURS",
        "DAYS"
    )
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(
                start = 4.dp,
                end = 4.dp
            )
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState = pagerState,
                        tabPositions = tabPositions
                    )
                )
            },
            backgroundColor = BlueLight
        ) {
            tabList.forEachIndexed { pageIndex, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = pageIndex
                            )
                        }
                    },
                    text = {
                        Text(
                            text = text
                        )
                    }
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) {

        }
    }
}