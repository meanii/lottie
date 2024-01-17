package com.owlers.lottie.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.owlers.lottie.ViewModel.LottiefileViewModel
import com.owlers.lottie.ui.theme.LottieTheme


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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 200.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
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