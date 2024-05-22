package at.htl.additionaltask.ui.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import at.htl.additionaltask.model.Model
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import at.htl.additionaltask.model.photos.Photo
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import at.htl.additionaltask.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.Modifier
import at.htl.additionaltask.model.post.Post

@Composable
fun PostScreen(model: Model) {

    var posts = model.posts

    if (posts.size == 0) {
        Text(
            text = "No Data"
        )
        return;
    }

    LazyColumn (
        modifier = Modifier.padding(16.dp)
    ) {
        items(posts.size){ index ->
            RowPost(index = index,post = posts[index])
            HorizontalDivider()
        }
    }

}

@Composable
fun RowPost(index: Int, post: Post){
    Row {
        Text(
            text = (index+1).toString()+": "+post.title
        )
    }
}