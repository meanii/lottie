package com.owlers.lottie.View

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.owlers.lottie.ViewModel.LottiefileViewModel
import com.owlers.lottie.ui.theme.CreamWhite


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lotties(
    viewModel: LottiefileViewModel = LottiefileViewModel()
) {
    val value by remember{ viewModel.repositories }

    LaunchedEffect(key1 = Unit) {
        viewModel.getLottieFiles()
    }

    val lottes = value.stocks.map { it.url }


    val pagerState = rememberPagerState(pageCount = {
        lottes.size
    })

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(lottes) { lottie ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 200.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Lottie(lotte = lottie)
            }
        }
    }

}

@Composable
fun Lottie(lotte: String) {
    val urlComposition by rememberLottieComposition(spec = LottieCompositionSpec.Url(lotte))
    val progress by animateLottieCompositionAsState(
        urlComposition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 200.dp),
        composition = urlComposition,
        progress = { progress },
    )
}