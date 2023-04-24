package com.toadfrogson.horrorhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader.Builder
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.delay

@Composable
fun ImageSlideShow(imageUrls: List<String>) {
    var index by remember { mutableStateOf(0) }
    var selectedImageUrl by remember { mutableStateOf(imageUrls[0]) }
    val context = LocalContext.current
    //TODO: image loader is recommended to be shared across the app
    val imageLoader = remember {
        Builder(context)
            .crossfade(true)
            .build()
    }
    val painter =
        rememberAsyncImagePainter(selectedImageUrl, imageLoader = imageLoader)
    LaunchedEffect(Unit) {
        while (true) {
            index = (index + 1) % imageUrls.size
            val nextImageRequest = ImageRequest.Builder(context).data(imageUrls[index]).build()
            imageLoader.enqueue(nextImageRequest)
            delay(7000)
            selectedImageUrl = imageUrls[index]
        }
    }
    //TODO: verify that caching works, should it be setup separetly in image loader builder?
    Image(painter = painter, contentDescription = "", modifier = Modifier.fillMaxWidth())

}