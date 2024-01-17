package com.owlers.lottie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieRetrySignal
import com.owlers.lottie.View.Lotties
import com.owlers.lottie.ui.theme.LottieTheme


data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val selected: Boolean = false,
    val unselectedIcon: ImageVector,
)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LottieTheme {

                val items = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        icon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        selected = true
                    ),
                    BottomNavigationItem(
                        title = "Search",
                        icon = Icons.Filled.Search,
                        unselectedIcon = Icons.Outlined.Search,
                    ),
                    BottomNavigationItem(
                        title = "Settings",
                        icon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                    ),
                )

                var selectedIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Scaffold(

                        bottomBar = {
                            NavigationBar (
                                containerColor = MaterialTheme.colorScheme.primary,
                            ) {
                                items.forEachIndexed() { index, item ->
                                    NavigationBarItem(
                                        selected = selectedIndex == index,
                                        icon = {
                                               BadgedBox(badge = {}) {
                                                   Icon(
                                                       imageVector = if (item.selected) item.icon else item.unselectedIcon,
                                                         contentDescription = item.title,
                                                   )
                                               }
                                        },
                                        label = {
                                            Text(
                                                text = item.title,
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        },
                                        onClick = {
                                            selectedIndex = index
                                        },
                                    )
                                }
                            }
                        },

                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                containerColor = MaterialTheme.colorScheme.secondary,
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        }

                    ) {

                    Column {
                            Lotties()
                        }
                    }

                }
            }
        }
    }
}

