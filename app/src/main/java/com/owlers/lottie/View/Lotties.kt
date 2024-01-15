package com.owlers.lottie.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.owlers.lottie.ViewModel.LottiefileViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lotties(
    viewModel: LottiefileViewModel = LottiefileViewModel(),
) {
    val value by remember{ viewModel.repositories }

    LaunchedEffect(key1 = Unit) {
        viewModel.getLottieFiles()
    }

    val lottes = value.stocks.map { it.url }

    val pagerState = rememberPagerState(pageCount = {
        lottes.size
    })

    HorizontalPager(state = pagerState) { page: Int ->
        Lottie(lotte = lottes[page])
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
        modifier = Modifier.fillMaxSize(),
        composition = urlComposition,
        progress = { progress },
    )
}