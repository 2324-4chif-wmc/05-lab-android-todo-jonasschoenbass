package at.htl.additionaltask.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.additionaltask.model.Model
import at.htl.additionaltask.model.Store
import at.htl.additionaltask.model.photos.PhotoService
import at.htl.additionaltask.model.post.PostDto
import at.htl.additionaltask.model.post.PostService
import at.htl.additionaltask.model.todo.TodoService


@Composable
fun HomeScreen(
    model: Model,
    store: Store,
    todoService: TodoService,
    photoService: PhotoService,
    postService: PostService
) {

    var todos = model.todos
    var photos = model.photos

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row (){
            Text(
                text = "Home",
                fontSize = 15.sp
            )
        }

        Row (){
            Text(
                text = "Todos Count - " + todos.size,
                fontSize = 10.sp
            )
        }

        Row (){
            Button(
                onClick = { todoService.getAll() }) {
                Text("load Todos")
            }
        }

        Row (){
            Button(
                onClick = { store.setTodos(arrayOf()) }) {
                Text("clear Todos")
            }
        }


        HorizontalDivider()


        Row (){
            Text(
                text = "Photo Count - " + photos.size,
                fontSize = 10.sp
            )
        }
        Row (){
            Button(
                onClick = { photoService.getAll() }) {
                Text("load Photos")
            }
        }
        Row {
            Text(
                text = "Amount of Pictures to load: " + model.uiState.amountLoadPictures
            )
        }
        Row {
            TextField(
                value = model.uiState.amountLoadPictures.toString(),
                onValueChange = { newValue ->
                    store.setPictureAmount(newValue.toIntOrNull() ?: 0)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        HorizontalDivider()

        Row (){
            Text(
                text = "Post Count - " + model.posts.size,
                fontSize = 10.sp
            )
        }
        Row (){
            Button(
                onClick = { postService.getAll() }) {
                Text("load Posts")
            }
        }

        var postTitle by remember { mutableStateOf("") }
        var postBody by remember { mutableStateOf("") }

        Row {
            TextField(
                value = postTitle,
                onValueChange = { value ->
                    postTitle = value
                },
                placeholder = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row {
            TextField(
                value = postBody,
                onValueChange = { value ->
                    postBody = value
                },
                placeholder = { Text("Body") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row {
            Button(
                onClick = {
                    postService.createPost(PostDto(1,postTitle,postBody),model.posts.size)
                    postTitle = ""
                    postBody = ""
                }) {
                Text("add Post")
            }
        }



    }

}