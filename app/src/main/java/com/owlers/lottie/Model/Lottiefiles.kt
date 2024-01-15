package com.owlers.lottie.Model

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val name: String,
    val url: String
)

@Serializable
data class LottieFileRepositories(
    val stocks: List<Stock>
)