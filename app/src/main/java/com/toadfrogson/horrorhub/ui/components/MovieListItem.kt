package com.toadfrogson.horrorhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.toadfrogson.horrorhub.domain.model.movie.MovieEntity

@Composable
fun MovieListItem(modifier: Modifier = Modifier, data: MovieEntity, onSelected: () -> Unit) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                onSelected()
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = getFullPosterUrl(data.poster_path.orEmpty()),
                contentDescription = data.original_title,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .weight(0.4f)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .weight(0.6f)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = data.title.orEmpty(),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = data.release_date.orEmpty(),
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = data.overview.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

//TODO make movie data class to transform entity
fun getFullPosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/original/" + posterPath
}

@Preview(showBackground = true)
@Composable
fun previewMovieItem() {

}