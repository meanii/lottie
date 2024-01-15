package com.owlers.lottie.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.owlers.lottie.Model.LottieFileRepositories
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class LottiefileViewModel: ViewModel() {

    val _repository:  MutableState<LottieFileRepositories> = mutableStateOf(
        LottieFileRepositories(
            stocks = listOf()
        )
    )

    val repositories: MutableState<LottieFileRepositories> = _repository

    fun getLottieFiles() {
        val url = "https://raw.githubusercontent.com/meanii/lottiefilesdb/main/json/collections.json"
        val headers: HashMap<String, String> = HashMap()

        Fuel.get(url).header(headers).responseJson{ request, response, result ->
            Log.d("DEBUG", request.toString())

            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.d("DEBUG", "ex: $ex")
                }

                is Result.Success -> {
                    val data = result.get()
                    val lottieFileRepositories = data.content
                    Log.d("DEBUG", "data: $lottieFileRepositories")

                    val tmp = Json.decodeFromString<LottieFileRepositories>(result.get().obj().toString())
                    _repository.value = tmp
                }

                else -> {
                    var tmp = LottieFileRepositories(
                        stocks = listOf()
                    )
                }

            }

        }

    }

}