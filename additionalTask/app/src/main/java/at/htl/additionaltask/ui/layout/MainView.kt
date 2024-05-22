package at.htl.additionaltask.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import at.htl.additionaltask.model.Model
import at.htl.additionaltask.model.Store
import at.htl.additionaltask.model.photos.PhotoService
import at.htl.additionaltask.model.post.PostService
import at.htl.additionaltask.model.todo.TodoService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainView {
    @Inject
    constructor(){}

    @Inject
    lateinit var store: Store;
    @Inject
    lateinit var todoService: TodoService;
    @Inject
    lateinit var photoService: PhotoService
    @Inject
    lateinit var postService: PostService

    fun buildContent(activity: ComponentActivity){
        var view = ComposeView(activity)

        view.setContent {
            val viewModel = store.pipe.observeOn(AndroidSchedulers.mainThread()).subscribeAsState(initial = Model()).value

            Surface (
                modifier = Modifier.fillMaxSize()
            ) {
                TabScreen(viewModel, store, todoService, photoService, postService)
            }

        }

        activity.setContentView(view)
    }

}
