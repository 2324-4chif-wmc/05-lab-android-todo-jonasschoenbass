package at.htl.additionaltask.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import at.htl.additionaltask.model.Model
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import at.htl.additionaltask.model.photos.Photo
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import at.htl.additionaltask.R
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun PhotosScreen(model: Model) {

    var photos = model.photos
    var amountToLoad = model.uiState.amountLoadPictures

    if (photos.size == 0) {
        Text(
            text = "No Data"
        )
        return;
    }

    LazyColumn (
        modifier = Modifier.padding(16.dp)
    ){
        items(amountToLoad){ index ->
            if(photos != null && photos.size >= index){
                RowPhoto(photo = photos[index])
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun RowPhoto(photo: Photo){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text =  "Titel: " + photo.title,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Row (){
        ImageFromUrl(photo.thumbnailUrl)
    }
}

@Composable
fun ImageFromUrl(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        contentDescription = stringResource(R.string.app_name),
        modifier = Modifier.fillMaxWidth()
    )
}