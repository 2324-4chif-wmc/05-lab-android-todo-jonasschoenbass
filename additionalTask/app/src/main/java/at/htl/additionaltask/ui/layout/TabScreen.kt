package at.htl.additionaltask.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.htl.additionaltask.model.Model
import at.htl.additionaltask.model.Store
import at.htl.additionaltask.model.photos.PhotoService
import at.htl.additionaltask.model.post.PostService
import at.htl.additionaltask.model.todo.TodoService

@Composable
fun TabScreen (
    model: Model,
    store: Store,
    todoService: TodoService,
    photoService: PhotoService,
    postService: PostService, ){

    var uiState = model.uiState
    var tabIndex = uiState.selectedTab
    var tabs = listOf("Home", "ToDos", "Photos", "Post")

    Column (
        modifier = Modifier.fillMaxSize()
    ) {

        TabRow(
            selectedTabIndex = tabIndex
        ) {
            tabs.forEachIndexed { index, titel ->
                Tab(
                    text = { Text(titel) },
                    selected = tabIndex == index,
                    onClick = { store.selectTab(index) },
                    icon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) }

                )
            }
        }

        when(tabIndex){
            0 -> HomeScreen(model, store, todoService, photoService, postService)
            1 -> TodoScreen(model, store)
            2 -> PhotosScreen(model)
            3 -> PostScreen(model)
        }

    }
}