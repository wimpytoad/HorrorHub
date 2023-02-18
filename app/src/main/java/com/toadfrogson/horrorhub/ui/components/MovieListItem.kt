package com.toadfrogson.horrorhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.toadfrogson.horrorhub.data.repo.movielist.model.MovieEntity

@Composable
fun MovieListItem(modifier: Modifier = Modifier, data: MovieEntity) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = getFullPosterUrl(data.poster_path.orEmpty()),
                contentDescription = data.original_title,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .weight(0.4f)
            )
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .weight(0.6f)) {
                Text(text = data.title.orEmpty())

                Text(modifier = Modifier.padding(top = 8.dp), text = data.release_date.orEmpty())
            }
        }
    }
}

//TODO make movie data class to transform entity
fun getFullPosterUrl(posterPath: String): String {
    val posterUrl =
        return "https://image.tmdb.org/t/p/original/" + posterPath
}

@Preview(showBackground = true)
@Composable
fun previewMovieItem() {

}